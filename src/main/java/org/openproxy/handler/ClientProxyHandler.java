/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openproxy.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpResponse;

/**
 *
 * @author dmitry
 */
public class ClientProxyHandler extends ChannelInboundHandlerAdapter {

    private final ChannelHandlerContext backCtx;

    public ClientProxyHandler(ChannelHandlerContext backCtx) {
        this.backCtx = backCtx;
    }
    
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof HttpResponse) {
            backCtx.writeAndFlush(msg);
        }
    }

}
