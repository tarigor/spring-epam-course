package com.epam.mavenTask.services;

import com.epam.mavenTask.instances.Department;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class DepartmentService implements IDepartmentService {

    HashMap<Integer, Department> departmentBase;
    DataBaseUpdater dataBaseUpdater;
    Scanner scanner;

    public DepartmentService() {
        departmentBase = new HashMap<>();
        dataBaseUpdater = new DataBaseUpdater(departmentBase);
        scanner = new Scanner(System.in);
    }

    @Override
    public void getAllDepartments() {
        dataBaseUpdater.getBaseListing(departmentBase);
    }

    @Override
    public void createDepartment(Department department) {
        departmentBase.put(department.getDepartmentId(), department);
    }

    @Override
    public void updateDepartment() {
        int idToBeModified;
        int selectedNameField;

        ArrayList<String> method = new ArrayList();
        System.out.println("select an ID of department desired to modify");

        dataBaseUpdater.getBaseListing(departmentBase);

        //input scanner code for the selection. idToBeModified
        idToBeModified = scanner.nextInt();
        System.out.println("select the name of field to be modified");
        int i = 1;
        for (Field field : Department.class.getDeclaredFields()) {
            System.out.println(i + " " + field.toString().split("\\.")[field.toString().split("\\.").length - 1]);
            method.add(field.toString().split("\\.")[field.toString().split("\\.").length - 1]);
            i++;
        }

        selectedNameField = scanner.nextInt();

        dataBaseUpdater.modifyDataBase(idToBeModified, method.get(selectedNameField - 1));

        dataBaseUpdater.getBaseListing(departmentBase);
    }

    @Override
    public void assignToDepartment(EmployeeService employeeService) {
        System.out.println("There is list of following departments");
        dataBaseUpdater.getBaseListing(departmentBase);
        System.out.println("There is a list of following users");
        employeeService.getDataBaseUpdater().getBaseListing(employeeService.getEmployeeDataBase());
        System.out.println("Select a user for whom will be changed a departmentID:");
        Integer selectedEmployeeID = scanner.nextInt();
        employeeService.getDataBaseUpdater().modifyDataBase(selectedEmployeeID,"departmentId");
        employeeService.getDataBaseUpdater().getBaseListing(employeeService.getEmployeeDataBase());
    }

    @Override
    public void getAllEmployees(EmployeeService employeeService) {
        DataBaseUpdater dataBaseUpdater = new DataBaseUpdater(employeeService.getEmployeeDataBase());
        dataBaseUpdater.getBaseListing(employeeService.getEmployeeDataBase());
    }
}
