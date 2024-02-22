package main;

import java.util.Arrays;

public class Employees {
    private String firstname;
    private String lastname;
    private String title;
    private String department;
    private int salary;

    public Employees(String firstname, String lastname, String title, String department, int salary){
        this.firstname = firstname;
        this.lastname = lastname;
        this.title = title;
        this.department = department;
        this.salary = salary;
    }

    public Employees() {
    }


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String toString(){
        return "Name: " + getFirstname() + " " + getLastname() + ", Title: " + getTitle() + ", Department: " + getDepartment()
    + ", Salary: " + getSalary();}




}
