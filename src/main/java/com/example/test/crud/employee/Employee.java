package com.example.test.crud.employee;


import com.example.test.crud.branch.Branch;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;
import java.awt.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity

@Table(name="employee")
@CrossOrigin("http://localhost:4200")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    long id;
    @Column(name="national_id")

    long nationalId;
    @Column(name="name")
    String name;
    @Column(name="age")
    int age;
    @Column(name="branch_id")
    long branchIdEmployeee;



    @ManyToOne
    @ElementCollection
    @CollectionTable
    @JoinColumn(name = "branch_id", referencedColumnName = "id", insertable = false, updatable = false)
    Branch emp;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getNationalId() {
        return nationalId;
    }

    public void setNationalId(long nationalId) {
        this.nationalId = nationalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Branch getEmp() {
        return emp;
    }

    public void setEmp(Branch emp) {
        this.emp = emp;
    }

    public long getBranchIdEmployeee() {
        return branchIdEmployeee;
    }

    public void setBranchIdEmployeee(long branchIdEmployeee) {
        this.branchIdEmployeee = branchIdEmployeee;
    }
}
