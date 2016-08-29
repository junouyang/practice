package org.test;

import javax.persistence.*;
import java.security.acl.Owner;
import java.util.List;

/**
 * Created by jun.ouyang on 8/28/16.
 */
@Entity
public class Phone {
    @Id
    private long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="owner_id")
    private Employee owner;

    @Column(name="number")
    private String number;

    public Phone( ){

    }

    public Phone(long id, String number) {
        this.id = id;
        this.number = number;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Employee getOwner() {
        return owner;
    }

    public void setOwner(Employee owner) {
        this.owner = owner;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
