package com.assemblers.app.Models;

public class Employee {
    private int empid;
    private String Fname;
    private String Lname;
    private String job_title;
    private String email;
    private double salary;
    private String ssn;
    public Employee(){}
    public Employee(int empid, String Fname, String Lname, String job_title, String email, double salary, String ssn) {
        this.empid = empid;
        this.Fname = Fname;
        this.Lname = Lname;
        this.job_title = job_title;
        this.email = email;
        this.salary = salary;
        this.ssn = ssn;
    }

    // Getters and Setters
    public int getEmpid() {
        return empid;
    }

    public void setEmpid(int empid) {
        this.empid = empid;
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String fname) {
        this.Fname = fname;
    }

    public String getLname() {
        return Lname;
    }

    public void setLname(String lname) {
        this.Lname = lname;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }
}

