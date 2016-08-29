package org.test;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by jun.ouyang on 8/28/16.
 */
@Entity
public class MyClass {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    String name;

    public MyClass(){}

    public MyClass(String name){
        this.name = name;
    }
}
