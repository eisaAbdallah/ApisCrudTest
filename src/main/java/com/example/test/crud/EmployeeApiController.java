package com.example.test.crud;

import com.example.test.crud.branch.Branch;
import com.example.test.crud.employee.Employee;
import com.example.test.crud.service.CrudEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class EmployeeApiController {


    final private CrudEmployeeService crudEmployeeService;

    @Autowired
    public EmployeeApiController(CrudEmployeeService crudEmployeeService) {
        this.crudEmployeeService = crudEmployeeService;
    }

    @RequestMapping("/addemployee")
    @ResponseBody
    @CrossOrigin("http://localhost:4200")
    public ResponseEntity<Object> AddEmployee(@RequestBody Employee employee, @RequestParam("branchId") long branchId) {

        employee.setBranchIdEmployeee(branchId);
        if (String.valueOf(employee.getNationalId()).length() < 16) {


            return new ResponseEntity<Object>("The National Id Should be 16 Digits", HttpStatus.BAD_REQUEST);

        }

        final Pattern arabicLettersPattern = Pattern.compile("[\\u0600-\\u06FF\\u0750-\\u077F]",
                Pattern.UNICODE_CASE | Pattern.CANON_EQ | Pattern.CASE_INSENSITIVE);
        final Matcher arabicLettersMatcher = arabicLettersPattern.matcher(employee.getName());
        if (!arabicLettersMatcher.find()) {
            return new ResponseEntity<Object>("Only Arabic name Allowed", HttpStatus.BAD_REQUEST);


        }
        employee.setNationalId(employee.getNationalId());
        employee.setName(employee.getName());
        crudEmployeeService.addEmployee(employee);

        return new ResponseEntity<Object>("USER ADDED SUCCEFULLY", HttpStatus.ACCEPTED);
    }

    @RequestMapping("/getemployees")
    @ResponseBody
    public List<Employee> getmployee() {
        List<Employee> employeeList = crudEmployeeService.getEmployees();



        return employeeList;

    }


    @RequestMapping("/getemployee/{employeeId}")
    @ResponseBody
    public List<Employee> getmployeeById(@PathVariable("employeeId") long id) {
        List<Employee> employeeList = crudEmployeeService.getEmployeeById(id);
        return employeeList;

    }

    @RequestMapping("/deleteemployee")
    public void deletemployeeById(@RequestParam("employeeId") long id) {
        crudEmployeeService.deleteEmployee(id);


    }

    @RequestMapping("/updateemployee/{employeeId}")
    @ResponseBody
    public ResponseEntity<Object> UpadtemployeeById(@PathVariable("employeeId") long id, @RequestBody Employee employee) {

        if (String.valueOf(employee.getNationalId()).length() < 16) {


            return new ResponseEntity<Object>("The National Id Should be 16 Digits Not =" + employee.getNationalId(), HttpStatus.BAD_REQUEST);

        }

        final Pattern arabicLettersPattern = Pattern.compile("[\\u0600-\\u06FF\\u0750-\\u077F]",
                Pattern.UNICODE_CASE | Pattern.CANON_EQ | Pattern.CASE_INSENSITIVE);
        final Matcher arabicLettersMatcher = arabicLettersPattern.matcher(employee.getName());
        if (!arabicLettersMatcher.find()) {
            return new ResponseEntity<Object>("Only Arabic name Allowed Not " + employee.getName(), HttpStatus.BAD_REQUEST);


        }
        crudEmployeeService.editEmployee(id, employee.getName(), employee.getNationalId(), employee.getAge(), employee.getBranchIdEmployeee());
        return new ResponseEntity<Object>("USER UPDATED SUCCESSFULLY " + employee.getName(), HttpStatus.ACCEPTED);
    }
}
