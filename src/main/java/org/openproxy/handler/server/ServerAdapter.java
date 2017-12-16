/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openproxy.handler.server;

import io.netty.channel.ChannelInboundHandlerAdapter;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author dmitry
 */
public class ServerAdapter extends ChannelInboundHandlerAdapter implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("I have got "+arg.getClass().getName());
    }
    
}
