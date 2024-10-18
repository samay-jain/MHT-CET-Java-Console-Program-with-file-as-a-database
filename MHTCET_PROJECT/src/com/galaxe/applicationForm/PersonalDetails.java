package com.galaxe.applicationForm;

import java.io.Serializable;

public class PersonalDetails implements Serializable{
	private static final long serialVersionUID = 1L;

	private String fullName, fatherName, motherName, gender, religion, nationality, address;
	private int pincode;
	private float familyIncome;
	
	public PersonalDetails(String fullName,String fatherName,String motherName,String gender,String religion,String nationality,String address, int pincode, float familyIncome) {
		this.fullName = fullName;
		this.fatherName = fatherName;
		this.motherName = motherName;
		this.gender = gender;
		this.religion = religion;
		this.nationality = nationality;
		this.address = address;
		this.pincode = pincode;
		this.familyIncome = familyIncome;
	}
	public PersonalDetails() {
		this.fullName = "Samay Jain";
		this.fatherName = "Shekhar";
		this.motherName = "myMother";
		this.gender = "Male";
		this.religion = "Jain";
		this.nationality = "Indian";
		this.address = "Bangalore";
		this.pincode = 987789;
		this.familyIncome = 123000;
	}
	
	public void displayPersonalDetails() {
		System.out.println("\nPersonal Details -");
		System.out.println("Full Name: "+this.fullName);
		System.out.println("Father's Name: "+this.fatherName);
		System.out.println("Mother's Name: "+this.motherName);
		System.out.println("Gender: "+this.gender);
		System.out.println("Religion: "+this.religion);
		System.out.println("Nationality: "+this.nationality);
		System.out.println("Address: "+this.address);
		System.out.println("Pincode: "+this.pincode);
		System.out.println("Family Income: "+this.familyIncome);
	}
	
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getMotherName() {
		return motherName;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public float getFamilyIncome() {
		return familyIncome;
	}
	public void setFamilyIncome(float familyIncome) {
		this.familyIncome = familyIncome;
	}

}
