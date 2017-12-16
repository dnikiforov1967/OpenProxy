/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openproxy.handler.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import org.openproxy.controller.ProxyControllerFactory;
import org.openproxy.controller.flow.FlowListener;
import org.openproxy.handler.server.ServerAdapter;

/**
 *
 * @author dnikiforov
 */
public class ClientAdapter extends ChannelInboundHandlerAdapter {

    private final ProxyControllerFactory proxyControllerFactory;
    private final FlowListener flowListener;

    public ClientAdapter(ProxyControllerFactory proxyControllerFactory) {
        this.proxyControllerFactory = proxyControllerFactory;
        this.flowListener=new FlowListener();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof HttpRequest) {
            HttpRequest request = (HttpRequest)msg;
            System.out.println("HttpRequest "+request.getUri());
            //TODO initiate proxy to server connection, handle request
            flowListener.addObserver(new ServerAdapter());
            
            flowListener.wakeUp(msg);
            
        } else if (msg instanceof HttpContent) {
            HttpContent content = (HttpContent)msg;
            final ByteBuf bb = content.content();
            flowListener.wakeUp(msg);
            //Write to server the content
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
