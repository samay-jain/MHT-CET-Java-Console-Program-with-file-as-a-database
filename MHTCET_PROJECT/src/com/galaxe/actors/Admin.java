package com.galaxe.actors;

import com.galaxe.functionalities.ApplicationFormManager;

public class Admin implements Actors{

	private static int counter = 98765467;
	private int id;
	private String name;
	private String email;
	private String password;
	private ApplicationFormManager applicationFormManager;
	
	
	public Admin(String name, String email, String password) {
		this.id = counter;
		counter += 1;
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public ApplicationFormManager getApplicationFormManager() {
		return applicationFormManager;
	}

	public void setApplicationFormManager(ApplicationFormManager applicationFormManager) {
		this.applicationFormManager = applicationFormManager;
	}

	@Override
	public void displayDetails() {
		System.out.println("\nAdmin details -");
		System.out.println("\nId: "+this.id+"\nName: "+this.name+"\nEmail: "+this.email);
	}
}
