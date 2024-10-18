package com.galaxe.applicationForm;

import java.io.Serializable;

public class AcademicDetails implements Serializable{
	private static final long serialVersionUID = 1L;

	private float sscPercentage, hscPercentage;
	private String sscBoardName, hscBoardName;
	
	public AcademicDetails(float sscPercentage, float hscPercentage, String sscBoardName, String hscBoardName) {
		this.sscPercentage = sscPercentage;
		this.hscPercentage = hscPercentage;
		this.sscBoardName = sscBoardName;
		this.hscBoardName = hscBoardName;
	}
	
	public AcademicDetails() {
		this.sscPercentage = 79;
		this.hscPercentage = (float) 70.46;
		this.sscBoardName = "Maharashtra State Board";
		this.hscBoardName = "Maharashtra State Board";
	}

	public void displayAcademicDetails() {
		System.out.println("\nAcademic Details -");
		System.out.println("SSC Board Name: "+this.sscBoardName);
		System.out.println("SSC Percentage: "+this.sscPercentage);
		System.out.println("HSC Board Name: "+this.hscBoardName);
		System.out.println("HSC Percentage: "+this.hscPercentage);
	}

	public float getSscPercentage() {
		return sscPercentage;
	}

	public void setSscPercentage(float sscPercentage) {
		this.sscPercentage = sscPercentage;
	}

	public float getHscPercentage() {
		return hscPercentage;
	}

	public void setHscPercentage(float hscPercentage) {
		this.hscPercentage = hscPercentage;
	}

	public String getSscBoardName() {
		return sscBoardName;
	}

	public void setSscBoardName(String sscBoardName) {
		this.sscBoardName = sscBoardName;
	}

	public String getHscBoardName() {
		return hscBoardName;
	}

	public void setHscBoardName(String hscBoardName) {
		this.hscBoardName = hscBoardName;
	}
}
