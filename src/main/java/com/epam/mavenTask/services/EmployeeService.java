package com.epam.mavenTask.services;

import com.epam.mavenTask.instances.Employee;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class EmployeeService implements IEmployeeService {
    private Employee employee;
    private HashMap<Integer, Employee> employeeDataBase;
    DataBaseUpdater dataBaseUpdater;
    Scanner scanner;

    public DataBaseUpdater getDataBaseUpdater() {
        return dataBaseUpdater;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public HashMap<Integer, Employee> getEmployeeDataBase() {
        return employeeDataBase;
    }

    public EmployeeService() {
        employeeDataBase = new HashMap<>();
        dataBaseUpdater = new DataBaseUpdater(employeeDataBase);
        scanner = new Scanner(System.in);
    }

    @Override
    public void createEmployee(Employee employee) {
        employeeDataBase.put(employee.getEmployeeId(), employee);
    }

    @Override
    public void updateEmployee() {
        int idToBeModified;
        int selectedNameField;

        ArrayList<String> method = new ArrayList();
        System.out.println("select an ID of user desired to modify");

        dataBaseUpdater.getBaseListing(employeeDataBase);

        //input scanner code for the selection. idToBeModified
        idToBeModified = scanner.nextInt();
        System.out.println("select the name of field to be modified");
        int i = 1;
        for (Field field : Employee.class.getDeclaredFields()) {
            System.out.println(i + " " + field.toString().split("\\.")[field.toString().split("\\.").length - 1]);
            method.add(field.toString().split("\\.")[field.toString().split("\\.").length - 1]);
            i++;
        }

        selectedNameField = scanner.nextInt();

        dataBaseUpdater.modifyDataBase(idToBeModified, method.get(selectedNameField - 1));

        dataBaseUpdater.getBaseListing(employeeDataBase);

    }

}
