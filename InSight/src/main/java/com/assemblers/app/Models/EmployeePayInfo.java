package com.assemblers.app.Models;
import java.util.Date;
public class EmployeePayInfo extends Employee {
    private Date payDate;
    private double earning;
    private double fed_tax;
    private double fed_med;
    private double retire_401K;
    private double health_care;
    public EmployeePayInfo(int empid, String Fname, String Lname,Date payDate, double earning, double fed_tax,double fed_med,double fex_tax,double retire_401K,double health_care){
        super.setEmpid(empid);
        super.setFname(Fname);
        super.setLname(Lname);
        this.payDate = payDate;
        this.earning = earning;
        this.fed_med = fed_med;
        this.fed_tax = fed_tax;
        this.retire_401K = retire_401K;
        this.health_care = health_care;
    }
    public int getEmpid(){
        return super.getEmpid();
    }
    public String Fname(){
        return super.getFname();
    }
    public String Lname(){
        return super.getLname();
    }
    public Date getPayDate() {
        return payDate;
    }
    public double getEarning() {
        return earning;
    }
    public double getFed_tax() {
        return fed_tax;
    }
    public double getFed_med() {
        return fed_med;
    }
    public double getRetire_401K() {
        return retire_401K;
    }
    public double getHealth_care() {
        return health_care;
    }
    
}
