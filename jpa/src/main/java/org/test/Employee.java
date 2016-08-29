package org.test;

import javax.persistence.*;
import java.util.List;

/**
 * Created by jun.ouyang on 8/28/16.
 */
@Entity
public class Employee {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "fistName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "dept")
    private String dept;

    @OneToMany(mappedBy = "owner")
    private List<Phone> phones;

    public Employee(){

    }

    public Employee(long id, String firstName, String lastName, String dept){
        this.setId(id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setDept(dept);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }
}