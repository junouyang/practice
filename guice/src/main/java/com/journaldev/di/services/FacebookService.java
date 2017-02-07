package com.journaldev.di.services;

import com.appdynamics.platform.persistence.TransactionAttribute;
import com.google.inject.Singleton;

import java.util.concurrent.Callable;

/**
 * Created by jun.ouyang on 11/10/16.
 */
@Singleton
public class FacebookService implements MessageService {

    @TransactionAttribute
    public boolean sendMessage(String msg, String receipient) {
        //some complex code to send Facebook message
//        System.out.println("Message sent to Facebook user "+receipient+" with message="+msg);
        new Test() {
            public void test1() {
                output(msg, receipient);
            }
        }.test();
        return true;
    }

    @TransactionAttribute
    void output(String msg, String receipient) {
        System.out.println("Message sent to Facebook user "+receipient+" with message="+msg);
    }

    static abstract class Test {
        public void test() {
            test1();
        }

        public abstract void test1( );
    }
}