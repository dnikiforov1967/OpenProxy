/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openproxy;

import org.openproxy.controller.impl.DefaultHttpControllerFactory;
import org.openproxy.handler.client.ClientHandler;

/**
 *
 * @author dnikiforov
 */
public class App {
	
	public static void main(String... args) {
		System.out.println("OpenProxy");
		new OpenProxy(8080, 2, 16).openServer(new ClientHandler(new DefaultHttpControllerFactory()));
	}
	
}
