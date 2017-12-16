/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openproxy.controller.flow;

import java.util.Observable;

/**
 *
 * @author dmitry
 */
public class FlowListener extends Observable {
    
    public void wakeUp(Object obj) {
        setChanged();
        this.notifyObservers(obj);
    }
}
