/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openproxy.handler.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpServerCodec;
import org.openproxy.controller.ProxyControllerFactory;

/**
 *
 * @author dnikiforov
 */
public class ClientHandler extends ChannelInitializer<SocketChannel> {

    private ProxyControllerFactory proxyControllerFactory;

    public ClientHandler(ProxyControllerFactory proxyControllerFactory) {
        this.proxyControllerFactory = proxyControllerFactory;
    }

    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast("decoder", new HttpRequestDecoder(1024,1024*32,8));
        ch.pipeline().addLast(new ClientAdapter(proxyControllerFactory));
    }
}
