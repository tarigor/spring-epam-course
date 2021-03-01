package com.epam.mavenTask.services;

import com.epam.mavenTask.instances.Employee;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Scanner;

public class DataBaseUpdater {

    HashMap dataBase;

    public HashMap getDataBase() {
        return dataBase;
    }

    public void setDataBase(HashMap dataBase) {
        this.dataBase = dataBase;
    }

    public DataBaseUpdater(HashMap dataBase) {
        this.dataBase = dataBase;
    }


    void modifyDataBase(Integer idToBeModified, String selectedNameField) {

        Scanner scanner = new Scanner(System.in);
        String ClassName = dataBase.get(idToBeModified).getClass().getName().split("\\.")[dataBase.get(idToBeModified).getClass().getName().split("\\.").length - 1];
        for (Method method : dataBase.get(idToBeModified).getClass().getDeclaredMethods()) {
            if (method.getName().contains("Id") & method.getName().contains("get") & method.getName().contains(ClassName)) {
                String methodName = "set" + Character.toString(selectedNameField.charAt(0)).toUpperCase() + selectedNameField.substring(1);
                for (int j = 0; j < Employee.class.getMethods().length; j++) {
                    if (methodName.contentEquals(dataBase.get(1).getClass().getMethods()[j].getName())) {
                        if (dataBase.get(1).getClass().getMethods()[j].toString().contains("Integer")) {
                            System.out.println("Input a value of INTEGER format");
                            Integer desiredIntegerValue = scanner.nextInt();
                            try {
                                dataBase.get(1).getClass().getMethods()[j].invoke(dataBase.get(idToBeModified), desiredIntegerValue);
                            } catch (IllegalAccessException | InvocationTargetException e) {
                                e.printStackTrace();
                            }
                            break;
                        }
                        if (dataBase.get(1).getClass().getMethods()[j].toString().contains("String")) {
                            System.out.println("Input a value of STRING format");
                            String desiredStringValue = scanner.nextLine();
                            try {
                                dataBase.get(1).getClass().getMethods()[j].invoke(dataBase.get(idToBeModified), desiredStringValue);
                            } catch (IllegalAccessException | InvocationTargetException e) {
                                e.printStackTrace();
                            }
                            break;
                        }
                    }
                }
            }
        }
    }

    public void getBaseListing(HashMap dataBase) {
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
