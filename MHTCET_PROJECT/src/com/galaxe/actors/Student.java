package com.galaxe.actors;

import java.io.Serializable;
import java.util.Objects;
import java.util.Random;

public class Student implements Actors, Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String fullName, email, password;
	
	public Student(int id, String fullName, String email, String password){

		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.password = password;
	}
	
	public Student(String fullName, String email, String password){
		Random random = new Random();
		int min=100000000, max=999999999;
		
		this.id = random.nextInt(max-min)+min;
		this.fullName = fullName;
		this.email = email;
		this.password = password;
	}
	
	@Override
	public boolean equals(Object obj) {
	    if(this == obj) 
	    	return true;
	    if(obj == null || getClass() != obj.getClass()) 
	    	return false;
	    Student student = (Student) obj;
	    return email.equals(student.email);
	}

    @Override
    public int hashCode() {
    	return Objects.hash(email);
    }
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void displayDetails() {
		System.out.println("Student ID: "+this.id+" Name: "+this.fullName+" Email: "+this.email);
	}

}
