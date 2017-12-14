/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openproxy.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import org.openproxy.controller.ProxyControllerFactory;

/**
 *
 * @author dnikiforov
 */
public class OpenProxyChildHandler extends ChannelInitializer<SocketChannel> {

	private ProxyControllerFactory proxyControllerFactory;
	
	public OpenProxyChildHandler(ProxyControllerFactory proxyControllerFactory) {
		this.proxyControllerFactory=proxyControllerFactory;
	}
	
	@Override
	public void initChannel(SocketChannel ch) throws Exception {
		ch.pipeline().addLast("codec", new HttpServerCodec());
        ch.pipeline().addLast("aggregator",
        new HttpObjectAggregator(512*1024));		
		ch.pipeline().addLast(new OpenProxyHandler(proxyControllerFactory));
	}
}
