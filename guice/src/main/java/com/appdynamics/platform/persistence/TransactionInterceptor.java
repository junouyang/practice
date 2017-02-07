package com.appdynamics.platform.persistence;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

/**
 * This is AOP method interceptor for custom TransactionAttribute invocation.
 * It wraps calls into Guice POJOs annotated with TransactionAttribute.
 */
public class TransactionInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(final MethodInvocation methodInvocation) throws Throwable {
//        TransactionAttribute attribute = readTransactionAttribute(methodInvocation);
        System.out.println("Intercepted");
        return methodInvocation.proceed();
    }

    TransactionAttribute readTransactionAttribute(MethodInvocation methodInvocation) {
        TransactionAttribute attribute;
        Method method = methodInvocation.getMethod();

        attribute = method.getAnnotation(TransactionAttribute.class);
        if (null == attribute) {
            throw new IllegalStateException("TransactionAttribute not found on " + method.getName());
        }

        return attribute;
    }
}
