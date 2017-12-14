/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openproxy.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import java.net.InetSocketAddress;
import org.openproxy.controller.ProxyController;
import org.openproxy.controller.ProxyControllerFactory;

/**
 *
 * @author dnikiforov
 */
public class OpenProxyHandler extends ChannelInboundHandlerAdapter {

	private final ProxyControllerFactory proxyControllerFactory;

	public OpenProxyHandler(ProxyControllerFactory proxyControllerFactory) {
		this.proxyControllerFactory = proxyControllerFactory;
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		if (msg instanceof FullHttpRequest) {
			final FullHttpRequest request = (FullHttpRequest) msg;
			final ProxyController proxyController = proxyControllerFactory.build();
			final HttpResponse response = proxyController.resolveClientRequest(request);
			if (response != null) {
				writeToClient(ctx, response);
			} else {
				//if the response was not specified, resolve target IP
				final InetSocketAddress targetAddress = proxyController.adviseTargetAddress();
				//TODO send message to target and get response
			}
		} else {
			super.channelRead(ctx, msg);
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		// Close the connection when an exception is raised.
		cause.printStackTrace();
		ctx.close();
	}

	private void writeToClient(ChannelHandlerContext ctx, HttpResponse response) {
		ctx.writeAndFlush(response);
	}

}
