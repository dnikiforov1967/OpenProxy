/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openproxy;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dnikiforov
 */
public class OpenProxy {

	private int listenThreads = 2;
	private int workerThreads = 16;
	private int listenPort = 80;
	private NioEventLoopGroup bossGroup;
	private NioEventLoopGroup workerGroup;
	private ChannelFuture sync;

	public OpenProxy(
			final int listenPort,
			final int listenThreads,
			final int workerThreads) {
		this.listenThreads = listenThreads;
		this.workerThreads = workerThreads;
		this.listenPort = listenPort;
	}

	public void openServer(ChannelHandler handler) {

		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				shutdown();
			}
		});

		bossGroup = new NioEventLoopGroup(listenThreads);
		workerGroup = new NioEventLoopGroup(workerThreads);
		ServerBootstrap bootStrap = new ServerBootstrap();
		bootStrap
				.group(bossGroup, workerGroup)
				.channel(NioServerSocketChannel.class)
				.childHandler(handler)
				.option(ChannelOption.SO_BACKLOG, 128)
				.childOption(ChannelOption.SO_KEEPALIVE, true);
		try {
			sync = bootStrap.bind(listenPort).sync();
			sync.channel().closeFuture().sync();
		} catch (InterruptedException ex) {
			Logger.getLogger(OpenProxy.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}

	}

	public void shutdown() {
		workerGroup.shutdownGracefully();
		bossGroup.shutdownGracefully();

		try {
			sync.channel().closeFuture().sync();
		} catch (InterruptedException e) {
		}
	}

}
