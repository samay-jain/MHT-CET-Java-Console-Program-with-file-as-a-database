package com.galaxe.applicationForm;

import java.io.Serializable;

public class ExamCenterLocationPreference implements Serializable {
	private static final long serialVersionUID = 1L;

	private String locationPref1, locationPref2, locationPref3, locationPref4;
	
	public ExamCenterLocationPreference(String locationPref1, String locationPref2, String locationPref3, String locationPref4) {
		this.locationPref1 = locationPref1;
		this.locationPref2 = locationPref2;
		this.locationPref3 = locationPref3;
		this.locationPref4 = locationPref4;
	}
	public ExamCenterLocationPreference() {
		this.locationPref1 = "Dhule";
		this.locationPref2 = "Jalgaon";
		this.locationPref3 = "Nashik";
		this.locationPref4 = "Pune";
	}
	public void displayLocationPreferenceDetails() {
		System.out.println("\nLocation Preference Details -");
		System.out.println("Preference City 1: "+this.locationPref1);
		System.out.println("Preference City 2: "+this.locationPref2);
		System.out.println("Preference City 3: "+this.locationPref3);
		System.out.println("Preference City 4: "+this.locationPref4);
	}
	public String getLocationPref1() {
		return locationPref1;
	}
	public void setLocationPref1(String locationPref1) {
		this.locationPref1 = locationPref1;
	}
	public String getLocationPref2() {
		return locationPref2;
	}
	public void setLocationPref2(String locationPref2) {
		this.locationPref2 = locationPref2;
	}
	public String getLocationPref3() {
		return locationPref3;
	}
	public void setLocationPref3(String locationPref3) {
		this.locationPref3 = locationPref3;
	}
	public String getLocationPref4() {
		return locationPref4;
	}
	public void setLocationPref4(String locationPref4) {
		this.locationPref4 = locationPref4;
	}
}
