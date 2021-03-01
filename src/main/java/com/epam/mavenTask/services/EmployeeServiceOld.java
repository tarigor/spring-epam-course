package com.epam.mavenTask.services;

import com.epam.mavenTask.instances.Employee;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class EmployeeServiceOld implements IEmployeeService {
    private Employee employee;
    private HashMap<Integer, Employee> employeeDataBase;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public HashMap<Integer, Employee> getEmployeeDataBase() {
        return employeeDataBase;
    }

    public EmployeeServiceOld() {
        employeeDataBase = new HashMap<>();
    }

    @Override
    public void createEmployee(Employee employee) {
        employeeDataBase.put(employee.getEmployeeId(), employee);
    }

    @Override
    public void updateEmployee() {
        int idToBeModified;
        int selectedNameField;
        Scanner scanner = new Scanner(System.in);

        ArrayList<String> method = new ArrayList();
        System.out.println("select an ID of user desired to modify");
        getEmployeeList(employeeDataBase);
        //input scanner code for the selection. idToBeModified
        idToBeModified = scanner.nextInt();
        System.out.println("select the name of field to be modified");
        int i = 1;
        for (Field field : Employee.class.getDeclaredFields()) {
            System.out.println(i + " " + field.toString().split("\\.")[field.toString().split("\\.").length - 1]);
            method.add(field.toString().split("\\.")[field.toString().split("\\.").length - 1]);
            i++;
        }
        //input scanner code for the selection. selectedNameField
        selectedNameField = scanner.nextInt();
        modifyEmployeeData(idToBeModified, method.get(selectedNameField - 1));
        getEmployeeList(employeeDataBase);

    }

    void modifyEmployeeData(Integer idToBeModified, String selectedNameField) {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < employeeDataBase.size(); i++) {
            if (idToBeModified.equals(employeeDataBase.get(i + 1).getEmployeeId())) {
                String methodName = "set" + Character.toString(selectedNameField.charAt(0)).toUpperCase() + selectedNameField.substring(1);
                for (int j = 0; j < Employee.class.getMethods().length; j++) {
                    if (methodName.contentEquals(Employee.class.getMethods()[j].getName())) {
                        if (Employee.class.getMethods()[j].toString().contains("Integer")) {
                            System.out.println("Input a value of INTEGER format");
                            Integer desiredIntegerValue = scanner.nextInt();
                            try {
                                Employee.class.getMethods()[j].invoke(employeeDataBase.get(i + 1), desiredIntegerValue);
                            } catch (IllegalAccessException | InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }
                        if (Employee.class.getMethods()[j].toString().contains("String")) {
                            System.out.println("Input a value of STRING format");
                            String desiredStringValue = scanner.nextLine();
                            try {
                                Employee.class.getMethods()[j].invoke(employeeDataBase.get(i + 1), desiredStringValue);
                            } catch (IllegalAccessException | InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

    public void getEmployeeList(HashMap dataBase) {
        for (int i = 0; i < dataBase.size(); i++) {
            for (Method method : dataBase.get(i + 1).getClass().getDeclaredMethods()) {
                if (method.getName().contains("get")) {
                    try {
                        System.out.print(method.invoke(dataBase.get(i + 1)) + " ");
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("");
        }
    }
}
