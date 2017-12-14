/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openproxy.controller;

import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import java.net.InetSocketAddress;

/**
 *
 * @author dnikiforov
 */
public interface ProxyController {
	
	HttpResponse resolveClientRequest(HttpRequest request);
	
	InetSocketAddress adviseTargetAddress();
	
}
