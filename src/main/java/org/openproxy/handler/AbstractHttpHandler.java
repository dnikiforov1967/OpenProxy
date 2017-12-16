/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openproxy.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.HttpObject;

/**
 *
 * @author dmitry
 */
public class AbstractHttpHandler extends SimpleChannelInboundHandler<Object> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object obj) throws Exception {
        readObj(obj);
    }
    
    protected void readObj(Object msg) {
        if (msg instanceof HttpObject) {
            
        }
    }
    
}
