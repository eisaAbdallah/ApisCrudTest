package com.example.test.crud.service;

import com.example.test.crud.branch.Branch;
import com.example.test.crud.employee.Employee;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
@CrossOrigin("http://localhost:4200")
public interface CrudEmployeeService {

    void addEmployee(Employee employeeDetails);
    List<Branch> getBranchById(long id);
    List<Employee> getEmployees();
    List<Employee> getEmployeeById(long id);
    void deleteEmployee(long id);
    void editEmployee(long id,String name,long NationalId,long age,long branchId);
}
