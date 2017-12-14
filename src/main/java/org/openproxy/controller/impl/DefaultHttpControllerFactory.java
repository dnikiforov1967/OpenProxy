/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openproxy.controller.impl;

import org.openproxy.controller.ProxyController;
import org.openproxy.controller.ProxyControllerFactory;

/**
 *
 * @author dnikiforov
 */
public class DefaultHttpControllerFactory implements ProxyControllerFactory {

	@Override
	public ProxyController build() {
		return new DefaultHttpController();
	}

}
