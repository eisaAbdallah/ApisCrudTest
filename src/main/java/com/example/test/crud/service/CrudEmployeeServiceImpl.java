package com.example.test.crud.service;

import com.example.test.crud.branch.Branch;
import com.example.test.crud.employee.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
@Service
@CrossOrigin("http://localhost:4200")
public class CrudEmployeeServiceImpl implements CrudEmployeeService{
  final private EntityManager entityManager;
  final private JdbcTemplate jdbcTemplate;

  @Autowired
    public CrudEmployeeServiceImpl(final EntityManager entityManager,final JdbcTemplate jdbcTemplate) {
        this.entityManager = entityManager;
        this.jdbcTemplate=jdbcTemplate;
    }

    @Override
    @Transactional
    public void addEmployee(Employee employeeDetails) {
        entityManager.merge(employeeDetails);
    }

    @Override
    @Transactional
    public List<Branch> getBranchById(long id) {

        Query branch=entityManager.createQuery("from Branch where id ="+id);
        List<Branch> branchlist=branch.getResultList();

        return branchlist;

    }

    @Override
    @Transactional
    public List<Employee> getEmployees() {

    Query employees=entityManager.createQuery("from Employee");
    List<Employee> employeeslist=employees.getResultList();

    return employeeslist;
    }

    @Override
    @Transactional
    public List<Employee> getEmployeeById(long id) {
        Query employees=entityManager.createQuery("from Employee where id="+id);
        List<Employee> employeeslist=employees.getResultList();

        return employeeslist;
    }

    @Override
    @Transactional
    public void deleteEmployee(long id) {
        Query employees=entityManager.createQuery("delete from Employee where id="+id);
        employees.executeUpdate();


    }

    @Override
    @Transactional
    public void editEmployee(long id,String name,long NationalId,long age,long branchId) {


if(name!=null&&!name.isEmpty()){


    Query employeesUpdate=entityManager.createQuery("update Employee set name='"+name+"' where id="+id);
    employeesUpdate.executeUpdate();
}
        if(NationalId!=0){


           Query employeesUpdatethird=entityManager.createQuery("update Employee set nationalId="+NationalId+" where id="+id);
            employeesUpdatethird.executeUpdate();
        }
        if(age!=0){


            Query  employeesUpdatefourth=entityManager.createQuery("update Employee set age="+age+"  where id="+id);
            employeesUpdatefourth.executeUpdate();
        }
        if(branchId!=0){


            Query employeesUpdatefifth=entityManager.createQuery("update Employee set branchId="+branchId+"  where id="+id);
            employeesUpdatefifth.executeUpdate();
        }


    }
}
