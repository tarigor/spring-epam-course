package com.epam.mavenTask.services;

import com.epam.mavenTask.instances.Employee;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public interface IEmployeeService {
    void createEmployee(Employee employee);
    void updateEmployee() throws InvocationTargetException, IllegalAccessException;
}
