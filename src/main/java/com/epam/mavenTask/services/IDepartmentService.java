package com.epam.mavenTask.services;

import com.epam.mavenTask.instances.Department;
import com.epam.mavenTask.instances.Employee;

import java.util.HashMap;

public interface IDepartmentService {
    void getAllDepartments();
    void createDepartment(Department department);
    void updateDepartment();
    void assignToDepartment(EmployeeService employeeService);
    void getAllEmployees(EmployeeService employeeService);
}
