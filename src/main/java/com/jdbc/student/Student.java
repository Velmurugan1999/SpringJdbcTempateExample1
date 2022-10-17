package com.jdbc.student;

import org.springframework.stereotype.Component;

@Component
public class Student {
	private int sid;
	private String name;
	private String department;
	private double cgpa;
	private String address;
	public Student() {
		
	}
	public Student(int sid, String name, String department, double cpa, String address) {
		super();
		this.sid = sid;
		this.name = name;
		this.department = department;
		this.cgpa = cpa;
		this.address = address;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public double getCpa() {
		return cgpa;
	}
	public void setCpa(double cpa) {
		this.cgpa = cpa;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Sid:"+this.getSid()+"    Name:"+this.getName()+"    Department"+this.getDepartment()+"    Cgpa:"+
				this.getCpa()+"   Address:"+this.getAddress();
	}
}
