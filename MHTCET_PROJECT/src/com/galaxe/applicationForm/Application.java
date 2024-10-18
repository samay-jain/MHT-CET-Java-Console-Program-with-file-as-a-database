package com.galaxe.applicationForm;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

import com.galaxe.actors.Student;

public class Application implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int applicationId;
	Student student;
	PersonalDetails personalDetails;
	AcademicDetails academicDetails;
	CourseAndMarks courseAndMarks;
	ExamCenterLocationPreference examCenterLocationPreference;
	Payment payment;
	
	private int rollNo;
	private LinkedHashMap<String, String>clgPreferenceMap;
	private String allocatedCollegeAndBranch="";
	
	public Application() {
		Random random = new Random();
		int min=100000000, max=999999999;
		this.applicationId = random.nextInt(max-min)+min;
		
		clgPreferenceMap = new LinkedHashMap<>();
	}

	public int getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	public PersonalDetails getPersonalDetails() {
		return personalDetails;
	}

	public void setPersonalDetails(PersonalDetails personalDetails) {
		this.personalDetails = personalDetails;
	}

	public AcademicDetails getAcademicDetails() {
		return academicDetails;
	}

	public void setAcademicDetails(AcademicDetails academicDetails) {
		this.academicDetails = academicDetails;
	}

	public CourseAndMarks getCourseAndMarks() {
		return courseAndMarks;
	}

	public void setCourseAndMarks(CourseAndMarks courseAndMarks) {
		this.courseAndMarks = courseAndMarks;
	}

	public ExamCenterLocationPreference getExamCenterLocationPreference() {
		return examCenterLocationPreference;
	}

	public void setExamCenterLocationPreference(ExamCenterLocationPreference examCenterLocationPreference) {
		this.examCenterLocationPreference = examCenterLocationPreference;
	}
	
	public void addPaymentDetails(Payment payment) {
		this.payment = payment;
	}
	
	public Payment getPayment() {
		return payment;
	}
	
	public int getRollNo() {
		return rollNo;
	}

	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}


	public void viewApplicationForm() {
		System.out.println("\nApplication ID: "+this.applicationId);
		personalDetails.displayPersonalDetails();
		academicDetails.displayAcademicDetails();
		courseAndMarks.displaySelectedCourse();
		examCenterLocationPreference.displayLocationPreferenceDetails();
	}
	
	public void viewAdmitCard() {
		System.out.println("______________________________________________________");
		System.out.println("\nYour Admit Card Details are -");
		System.out.println("Application ID: "+this.applicationId+" RollNo: "+this.rollNo);
	}
	
	
	public void attemptExam() {
		System.out.println("______________________________________________________");
		System.out.println("\nYou have successfully attempted the CET Exam.");
	}
	

	public LinkedHashMap<String, String> getClgPreferenceMap() {
		return clgPreferenceMap;
	}

	public void setClgPreferenceMap(LinkedHashMap<String, String> clgPreferenceMap) {
		this.clgPreferenceMap = clgPreferenceMap;
	}

	public String getAllocatedCollegeAndBranch() {
		return allocatedCollegeAndBranch;
	}

	public void setAllocatedCollegeAndBranch(String allocatedCollegeAndBranch) {
		this.allocatedCollegeAndBranch = allocatedCollegeAndBranch;
	}
	
	public void displayAllocatedCollegeAndBranch() {
		System.out.println("Application ID: "+this.applicationId+"\n"+this.allocatedCollegeAndBranch);
	}
	
}
