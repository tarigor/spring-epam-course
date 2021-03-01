package com.epam.mavenTask;

import com.epam.mavenTask.instances.Department;
import com.epam.mavenTask.instances.Employee;
import com.epam.mavenTask.services.DepartmentService;
import com.epam.mavenTask.services.EmployeeService;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Main {


    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        EmployeeService employeeService = new EmployeeService();
        DepartmentService departmentService = new DepartmentService();

        employeeService.createEmployee(new Employee(1,"Igor",100));
        employeeService.createEmployee(new Employee(2,"Ivan",101));
        employeeService.createEmployee(new Employee(3,"Egor",100));

        departmentService.createDepartment(new Department(1,"department1"));
        departmentService.createDepartment(new Department(2,"department2"));

//        employeeService.updateEmployee();
//        departmentService.updateDepartment();

        departmentService.assignToDepartment(employeeService);

        Map map =  new LinkedHashMap();

    }
}
