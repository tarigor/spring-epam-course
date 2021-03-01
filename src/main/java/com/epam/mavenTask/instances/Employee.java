package com.epam.mavenTask.instances;

public class Employee {
    private Integer employeeId;
    private String employeeName;
    private Integer departmentId;

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Employee(Integer employeeId, String employeeName, Integer departmentId) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "1. employeeId=" + employeeId +
                ", 2. employeeName='" + employeeName + '\'' +
                ", 3. departmentId=" + departmentId +
                '}';
    }
}
