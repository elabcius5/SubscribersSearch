package com.globant.tutoria.subscriberssearch.bus;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by juan.herrera@globant.com on 26/08/2016.
 */
public class BusProvider {
    private static final Bus BUS = new Bus(ThreadEnforcer.ANY);

    public static Bus getInstance(){
        return BUS;
    }
}
