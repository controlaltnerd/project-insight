package com.assemblers.app.Models;
import java.util.Date;

public class Employee {
    private int empid;
    private String Fname;
    private String Lname;
    private String job_title;
    private Date pay_date;
    private double earnings;
    private double fed_tax;
    private double fed_med;
    private double fed_SS;
    private double state_tax;
    private double retire_401K;
    private double health_care;

    // Constructor
    public Employee(int empid, String Fname, String Lname, String job_title, Date pay_date, double earnings,
                    double fed_tax, double fed_med, double fed_SS, double state_tax, double retire_401K, double health_care) {
        this.empid = empid;
        this.Fname = Fname;
        this.Lname = Lname;
        this.job_title = job_title;
        this.pay_date = pay_date;
        this.earnings = earnings;
        this.fed_tax = fed_tax;
        this.fed_med = fed_med;
        this.fed_SS = fed_SS;
        this.state_tax = state_tax;
        this.retire_401K = retire_401K;
        this.health_care = health_care;
    }

    // Getters
    public int getEmpid() { return empid; }
    public String getFname() { return Fname; }
    public String getLname() { return Lname; }
    public String getJobTitle() { return job_title; }
    public Date getPayDate() { return pay_date; }
    public double getEarnings() { return earnings; }
    public double getFedTax() { return fed_tax; }
    public double getFedMed() { return fed_med; }
    public double getFedSS() { return fed_SS; }
    public double getStateTax() { return state_tax; }
    public double getRetire401K() { return retire_401K; }
    public double getHealthCare() { return health_care; }
}
