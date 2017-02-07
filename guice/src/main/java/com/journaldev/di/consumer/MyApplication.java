package com.journaldev.di.consumer;

import javax.inject.Inject;

//import com.google.inject.Inject;
import com.journaldev.di.services.MessageService;

public class MyApplication {

    private MessageService service;

    //setter method injector
    @Inject
    public void setService(MessageService svc){
        this.service=svc;
    }

    public boolean sendMessage(String msg, String rec){
        //some business logic here
        return service.sendMessage(msg, rec);
    }
}