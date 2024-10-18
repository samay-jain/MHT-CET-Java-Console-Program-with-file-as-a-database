package com.galaxe.actors;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Random;

public class College implements Actors, Serializable{

	private static final long serialVersionUID = 1L;

	private int id;
	private String collegeName;
	private HashMap<String, Integer>branchAndSeats;
	private String location;
	private String password;
	private String course;
	
	

	public College() {
		Random random = new Random();
		int min=100000000, max=999999999;
		
		this.id = random.nextInt(max-min)+min;
		
		this.collegeName = "Walchand College of Engineering";
		this.course = "Engineering";
		this.branchAndSeats = new HashMap<>();
		branchAndSeats.put("CSE",2);
		branchAndSeats.put("IT",3);
		branchAndSeats.put("ENTC",1);
		branchAndSeats.put("Electronics",1);
		branchAndSeats.put("ECE",2);
		this.location = "Sangli";
		this.password = "college";
	}
	
	public College(String collegeName, String course, HashMap<String,Integer>branchAndSeats, String location, String password) {
		Random random = new Random();
		int min=100000000, max=999999999;
		
		this.id = random.nextInt(max-min)+min;
		
		this.collegeName = collegeName;
		this.course = course;
		this.branchAndSeats = branchAndSeats;
		this.location = location;
		this.password = password;
	}
	
	
	@Override
	public void displayDetails() {
		System.out.println("\nCollege ID: "+this.id+" Name: "+this.collegeName+" Location: "+this.location+" Course: "+this.course);
	}
	
	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public HashMap<String, Integer> getBranchAndSeats() {
		return branchAndSeats;
	}

	public void setBranchAndSeats(HashMap<String, Integer> branchAndSeats) {
		this.branchAndSeats = branchAndSeats;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
