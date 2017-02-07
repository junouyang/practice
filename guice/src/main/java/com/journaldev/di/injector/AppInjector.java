package com.journaldev.di.injector;

import com.appdynamics.platform.NonSyntheticMethodMatcher;
import com.appdynamics.platform.persistence.TransactionAttribute;
import com.appdynamics.platform.persistence.TransactionInterceptor;
import com.google.inject.AbstractModule;
import com.journaldev.di.services.FacebookService;
import com.journaldev.di.services.MessageService;
import org.aopalliance.intercept.MethodInterceptor;

import static com.google.inject.matcher.Matchers.annotatedWith;
import static com.google.inject.matcher.Matchers.any;

public class AppInjector extends AbstractModule {

    @Override
    protected void configure() {
        //bind the service to implementation class
        //bind(MessageService.class).to(EmailService.class);

        //bind MessageService to Facebook Message implementation
        bind(MessageService.class).to(FacebookService.class);

        MethodInterceptor transactionInterceptor = new TransactionInterceptor();

        bindInterceptor(any(), NonSyntheticMethodMatcher.INSTANCE.and(annotatedWith(TransactionAttribute.class)),
                transactionInterceptor);
        requestInjection(transactionInterceptor);

    }

}