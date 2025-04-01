package com.assemblers.app.Models;
public class User {
    private int empid;
    private int role;
    public User(int empid, int role) {
        this.empid = empid;
        this.role = role;
    }
    public int getEmpid() {
        return empid;
    }
    public int getRole() {
        return role;
    }
}
