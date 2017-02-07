/*
 * Copyright (c) AppDynamics, Inc., and its affiliates
 * 2016
 * All Rights Reserved
 * THIS IS UNPUBLISHED PROPRIETARY CODE OF APPDYNAMICS, INC.
 * The copyright notice above does not evidence any actual or intended publication of such source code
 */

package com.appdynamics.platform;

import com.google.inject.matcher.AbstractMatcher;

import java.lang.reflect.Method;

/**
 * Method matcher excluding synthetic methods
 * context: https://github.com/google/guice/issues/640
 *
 * @author mark.lu on 5/6/16
 */
public class NonSyntheticMethodMatcher extends AbstractMatcher<Method> {
    public static final NonSyntheticMethodMatcher INSTANCE = new NonSyntheticMethodMatcher();

    private NonSyntheticMethodMatcher() {
    }

    @Override
    public boolean matches(Method method) {
        return !method.isSynthetic();
    }
}
