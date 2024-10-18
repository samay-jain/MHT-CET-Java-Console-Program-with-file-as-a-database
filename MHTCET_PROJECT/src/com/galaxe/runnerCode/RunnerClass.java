package com.galaxe.runnerCode;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import com.galaxe.actors.Admin;
import com.galaxe.actors.College;
import com.galaxe.actors.Student;
import com.galaxe.applicationForm.AcademicDetails;
import com.galaxe.applicationForm.Application;
import com.galaxe.applicationForm.CourseAndMarks;
import com.galaxe.applicationForm.ExamCenterLocationPreference;
import com.galaxe.applicationForm.Payment;
import com.galaxe.applicationForm.PersonalDetails;
import com.galaxe.functionalities.*;

public class RunnerClass {

	public static void main(String[] args) {
		
		RegisterAndLogin registerAndLogin = new RegisterAndLogin();
		ApplicationFormManager applicationFormManager = new ApplicationFormManager();
		RunnerClass runnerClass = new RunnerClass();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Welcome to Samay's Common Entrance Exam Application!");
		while(true)
		{
			System.out.println("______________________________________________________");			

			System.out.println("\nSelect an option -");
			System.out.println("1.Registration\n2.Login\n3.Other Exam Application options\n4.Exit");
			System.out.print("\nEnter your choice: ");
			int choice=sc.nextInt();
			
			try {
				if(choice==1)
					runnerClass.userRegistration(registerAndLogin, sc);
				else if(choice==2)
					runnerClass.userLogin(registerAndLogin, sc, runnerClass, applicationFormManager);
				else if(choice==3)
					runnerClass.otherExamApplicationOptions(registerAndLogin, sc, applicationFormManager, runnerClass);
				else if(choice==4)
					System.exit(0);
				else
					System.out.println("\nPlease select a valid option!");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	
	private void otherExamApplicationOptions(RegisterAndLogin registerAndLogin, Scanner sc, ApplicationFormManager applicationFormManager, RunnerClass runnerClass) {
		while(true)
		{
			System.out.println("______________________________________________________");

			System.out.println("\nSelect MHT-CET Exam operations -");
			System.out.println("1.View Admit Card\n2.View Score Card\n3.Add/View College Preferences\n4.College Allocation Status\n5.Display all college details\n6.Go back");
			System.out.print("\nEnter your choice: ");
			int userChoice=sc.nextInt();
			if(userChoice==1)
			{
				//View Admit Card
				System.out.println("______________________________________________________");

				System.out.println("To view Admit card enter the below details: ");
				System.out.print("Application ID: ");
				int applicationId = sc.nextInt();
				
				applicationFormManager.viewAdmitCard(applicationId);
				
			}
			else if(userChoice==2)
			{
				//View Score Card
				System.out.println("______________________________________________________");

				System.out.println("To view Score card enter the below details: ");
				System.out.print("Application ID: ");
				int applicationId = sc.nextInt();
				
				applicationFormManager.viewScoreCard(applicationId);
			}
			else if(userChoice==3)
			{	
				System.out.println("______________________________________________________");
				
				

				System.out.println("To view/add College preference enter the below details: ");
				System.out.print("Application ID: ");
				
				int applicationId = sc.nextInt();
				
				if(applicationFormManager.getApplicationById(applicationId).getRollNo()>0 && applicationFormManager.getApplicationById(applicationId).getCourseAndMarks().getRankObtainedMap().size()>0)
				{
					runnerClass.addCollegePreferences(applicationFormManager, sc, applicationId, registerAndLogin);
				}
				else
					System.out.println("\nPlease wait for Admit Card/Result to be released!");
					
			}
			else if(userChoice==4)
			{
				//College allocation status
				System.out.println("______________________________________________________");

				System.out.println("To view College Allocation status enter the below details: ");
				System.out.print("Application ID: ");
				int applicationId = sc.nextInt();

				if(applicationFormManager.isCollegePreferenceAdded(applicationId)==false)
					System.out.println("\nPlease add College preference First!");
				else
				{
					applicationFormManager.showCollegeAllocation(applicationId);
				}
			}
			else if(userChoice==5)
			{
				System.out.println("______________________________________________________");
				applicationFormManager.displayAllCollegeDetails(registerAndLogin);
			}
			else if(userChoice==6)
				break;
			else
				System.out.println("\nPlease select a valid option!");
		}
	}


	private void userRegistration(RegisterAndLogin registerAndLogin, Scanner sc) {
		while(true)
		{
			System.out.println("______________________________________________________");			

			System.out.println("\nSelect user type for registration -");
			System.out.println("1.Student registration\n2.College registration\n3.Admin\n4.go back");
			System.out.print("Enter your choice: ");
			int userType = sc.nextInt();
			
			System.out.println("______________________________________________________");			
			
			if(userType==1)
			{
				System.out.println("\nEnter below details for Student Registration -");
				sc.nextLine();
				System.out.print("Name: ");
				String name = sc.nextLine();
				System.out.print("Email ID: ");
				String email = sc.nextLine();
				System.out.print("Password: ");
				String password = sc.nextLine();
				
				Student student = new Student(name, email, password);
				
				registerAndLogin.registerStudent(student);
				break;
			}
			else if(userType==2)
			{
				System.out.println("\nEnter below details for College Registration -");
				sc.nextLine();
				System.out.print("College Name: ");
				String name = sc.nextLine();
				
				String course="";
				while(true)
				{
					System.out.println("Select Course -\n1.Engineering\n2.Medical");
					System.out.print("Choose any option: ");
					int option = sc.nextInt();
					
					if(option==1)
					{
						course = "Engineering";
						break;
					}
					else if(option==2)
					{
						course = "Medical";
						break;
					}
					else
						System.out.println("\nPlease choose a correct option");
				}
				
				
				
				System.out.print("Enter number of Branch: ");
				int noBranch = sc.nextInt();
				
				HashMap<String, Integer>branchAndSeats = new HashMap<>();
				for(int i=0;i<noBranch;i++)
				{
					sc.nextLine();
					System.out.print("Branch Name: ");
					String branchName = sc.nextLine();
					System.out.print("Available Seats: ");
					int seats = sc.nextInt();
					branchAndSeats.put(branchName, seats);
				}
				sc.nextLine();
				System.out.print("Location: ");
				String location = sc.nextLine();
				System.out.print("Password: ");
				String password = sc.nextLine();
				
				College college = new College(name, course, branchAndSeats, location, password);

				System.out.println("college password: "+college.getPassword());
				registerAndLogin.registerCollege(college);
				break;
			}
			else if(userType==3)
			{
				System.out.print("\nEnter security code for Admin Registration: ");
				int code = sc.nextInt();
				if(code==72629)
				{
					System.out.println("\nEnter below details for Admin Registration -");
					sc.nextLine();
					System.out.print("Name: ");
					String name = sc.nextLine();
					System.out.print("Email ID: ");
					String email = sc.nextLine();
					System.out.print("Password: ");
					String password = sc.nextLine();
					
					Admin admin = new Admin(name, email, password);
					
					registerAndLogin.registerAdmin(admin);
					break;
				}
				else
				{
					System.out.println("\nYou can't register as Admin!");
					break;
				}
				
			}
			else if(userType==4)
			{
				break;
			}
			else
				System.out.println("\nPlease select a valid user");
		}	}

	private void userLogin(RegisterAndLogin registerAndLogin, Scanner sc, RunnerClass runnerClass, ApplicationFormManager applicationFormManager) 
	{
		System.out.println("______________________________________________________");			

		System.out.println("\nSelect login -");
		System.out.println("1.Student\n2.College\n3.Admin\n4.go back");
		System.out.print("\nEnter your choice: ");
		int userType = sc.nextInt();
			
		System.out.println("______________________________________________________");			
			
		if(userType==1)
		{
			System.out.println("\nEnter below details for Student Login -");
			sc.nextLine();
			System.out.print("Enter registered email ID: ");
			String email = sc.nextLine();
			System.out.print("Enter password: ");
			String password = sc.nextLine();
				
			boolean isLoggedIn = registerAndLogin.studentLogin(email, password);
				
			if(isLoggedIn)
			{
				Student student = registerAndLogin.getStudentMap().get(email);
				student.displayDetails();
				runnerClass.manageApplicationForm(applicationFormManager, sc, runnerClass, student, registerAndLogin);
					
			}
			else
				System.out.println("\nPlease log in to the application!");
		}
		else if(userType==2)
		{
			System.out.println("\nEnter below details for College Login -");
			System.out.print("Enter College ID: ");
			int collegeID = sc.nextInt();
			sc.nextLine();
			System.out.print("Enter password: ");
			String password = sc.nextLine();
				
			boolean isLoggedIn = registerAndLogin.collegeLogin(collegeID, password);
			
			if(isLoggedIn)
			{
				
			}
		}
		else if(userType==3)
		{
			System.out.println("\nEnter below details for Admin Login -");
			System.out.print("Enter Email: ");
			String email = sc.next();
			sc.nextLine();
			System.out.print("Enter password: ");
			String password = sc.nextLine();
				
			boolean isAdminLoggedIn = registerAndLogin.adminLogin(email, password);
			if(isAdminLoggedIn)
			{
				adminFunctionalities(applicationFormManager, registerAndLogin, sc);
			}
		}
		else if(userType==4)
		{
			return;
		}
		else
			System.out.println("\nPlease select a valid user");
		
	}

	private void adminFunctionalities(ApplicationFormManager applicationFormManager, RegisterAndLogin registerAndLogin, Scanner sc) {
		
		while(true)
		{
			System.out.println("______________________________________________________");	
			
			System.out.println("\n1.Generate Admit Card\n2.Generate Results\n3.Allocate Seats\n4.View college branch-seats\n5.Go back");
			System.out.print("\nEnter your choice: ");
			int choice = sc.nextInt();
			
			System.out.println("\n_____________________________________________");

			if(choice==1)
			{
				boolean isGenerated = applicationFormManager.generateAdmitCard();
				if(isGenerated)
					System.out.println("\nAdmit cards are generated successfully!");
				else
					System.out.println("\nAdmit cards are already generated!");

			}
			else if(choice==2)
			{
				boolean isGenerated = applicationFormManager.generateScoreCard();
				if(isGenerated)
					System.out.println("\nScore cards are generated successfully!");
			}
			else if(choice==3)
			{
				applicationFormManager.allocateCollegeBasedOnRanking(registerAndLogin);
			}
			else if(choice==4)
			{
				applicationFormManager.viewAllCollegeBranchAndSeats(registerAndLogin);
			}
			else if(choice==5)
				break;
			else
				System.out.println("\nPlease enter a valid number!");
		}
	}


	private void addCollegePreferences(ApplicationFormManager applicationFormManager, Scanner sc, int applicationId, RegisterAndLogin registerAndLogin) {
		
		if(applicationFormManager.isCollegePreferenceAdded(applicationId))
		{
			applicationFormManager.viewCollegePreferences(applicationId);
			return;
		}
		
		
		try {
			System.out.println("\n_____________________________________________");
			System.out.println("Add College Preferences from below list (enter index and -1 to submit)-");
				
			LinkedHashMap<String, String>clgNameAndBranch = applicationFormManager.displayCollegeAndBranchListForPreference(registerAndLogin, applicationId);
			LinkedHashMap<String, String>map = new LinkedHashMap<>();
			
			int clgIndex = 0;
			int priorityNo=1;
			while(clgIndex!=-1)
			{
				clgIndex=sc.nextInt();
				
				if(clgIndex==-1)
					break;
				
				int count=0;
				for(Map.Entry<String, String>kv: clgNameAndBranch.entrySet())
				{
					if(count==clgIndex-1)
					{
						String clgName[] = kv.getKey().split("\\.");
						map.put(priorityNo+"."+clgName[1], kv.getValue());
						priorityNo+=1;
					}
					count+=1;
				}
			}
					
			applicationFormManager.addCollegePreferences(map, applicationId);
			System.out.println("College Preferences are added successfully!");
			return;
		}
		catch(Exception e)
		{
			System.out.println("\nPlease enter valid college preferences!");
		}
	}


	private void manageApplicationForm(ApplicationFormManager applicationFormManager, Scanner sc, RunnerClass runnerClass, Student student, RegisterAndLogin registerAndLogin) {		
		while(true)
		{
			System.out.println("______________________________________________________");			

			System.out.println("\nSelect an option: ");
			System.out.println("1.Create an Application\n2.View Existing Application\n3.Go Back");
			System.out.print("\nEnter your choice: ");
			int choice=sc.nextInt();
						
			
			if(choice==1)
			{
				while(true)
				{
					System.out.println("______________________________________________________");			
					
					System.out.println("\nTo create an Application choose options from below -");
					System.out.println("1.Fill Data\n2.Auto create an Application(dummy data)\n3.Go Back");
					System.out.print("\nEnter your choice: ");
					choice = sc.nextInt();
					
					System.out.println("______________________________________________________");			

					if(choice==1)
					{
						Application application = new Application();

						String fullName, fatherName, motherName, gender, religion, nationality, address;
						int pincode;
						float familyIncome;
						
						System.out.println("\nPlease enter below Personal Details -");
						sc.nextLine();
						System.out.print("Full Name: ");
						fullName = sc.nextLine();
						System.out.print("Father's Name: ");
						fatherName = sc.nextLine();
						System.out.print("Mother's Name: ");
						motherName = sc.nextLine();
						System.out.print("Gender: ");
						gender = sc.nextLine();
						System.out.print("Religion: ");
						religion = sc.nextLine();
						System.out.print("Nationality: ");
						nationality = sc.nextLine();
						System.out.print("Permanent Address: ");
						address = sc.nextLine();
						System.out.print("Pincode: ");
						pincode = sc.nextInt();
						System.out.print("Annual Family Income: ");
						familyIncome = sc.nextFloat();
						
						
						float sscPercentage, hscPercentage;
						String sscBoardName, hscBoardName;
						
						System.out.println("\nPlease enter below Academic Details -");
						sc.nextLine();
						System.out.print("SSC Board Name: ");
						sscBoardName = sc.nextLine();
						System.out.print("SSC Percentage: ");
						sscPercentage = sc.nextFloat();
						
						sc.nextLine();
						System.out.print("HSC Board Name: ");
						hscBoardName = sc.nextLine();
						System.out.print("HSC Percentage: ");
						hscPercentage = sc.nextFloat();
						
						
						System.out.println("\nPlease select course from below -");
						HashMap<String, Integer>map = new HashMap<>();

						System.out.println("1.PCM\n2.PCB\n3.Go back");
						System.out.print("Select an option: ");
						int option = sc.nextInt();
							
						if(option==1)
						{
							map.put("PCM", 10089);
							System.out.println("\nPCM is selected");
						}
						else if(option==2)
						{
							map.put("PCB", 10090);
							System.out.println("\nPCB is selected");
						}
						else if(option==3)
							break;
						else
						{
							System.out.println("\nPlease enter a valid option!");
						}
		
						
						String pref1, pref2, pref3, pref4;
						System.out.println("\nPlease enter exam center pereference Details -");
						sc.nextLine();
						System.out.print("City preference 1: ");
						pref1 = sc.nextLine();
						System.out.print("City preference 2: ");
						pref2 = sc.nextLine();
						System.out.print("City preference 3: ");
						pref3 = sc.nextLine();
						System.out.print("City preference 4: ");
						pref4 = sc.nextLine();
						
						
						Payment payment=null;
						System.out.println("\nPlease submit the application by making Payment of Rs.910 -");

						
						System.out.println("______________________________________________________");			
						System.out.println("1.Make Payment\n2.Cancel and go back");
						System.out.print("Enter your choice: ");
						option = sc.nextInt();
							
						System.out.println("______________________________________________________");			
							
						if(option==1)
						{
							payment = runnerClass.makePayment(910, "Online", "CET Application Form");
							
							application.setStudent(student);
							
							PersonalDetails personalDetails = new PersonalDetails(fullName, fatherName, motherName, gender, religion, nationality, address, pincode, familyIncome);
							application.setPersonalDetails(personalDetails);

							AcademicDetails academicDetails = new AcademicDetails(sscPercentage, hscPercentage, sscBoardName, hscBoardName);
							application.setAcademicDetails(academicDetails);
							
							CourseAndMarks courseAndMarks = new CourseAndMarks();
							courseAndMarks.setCourseNameAndId(map);
							application.setCourseAndMarks(courseAndMarks);
							
							ExamCenterLocationPreference examCenter = new ExamCenterLocationPreference(pref1, pref2, pref3, pref4);
							application.setExamCenterLocationPreference(examCenter);
							
							application.addPaymentDetails(payment);
							
							if(application!=null && application.getStudent()!=null && application.getPersonalDetails()!=null && application.getAcademicDetails()!=null && application.getCourseAndMarks()!=null && application.getExamCenterLocationPreference()!=null && application.getPayment()!=null)
							{
								applicationFormManager.createApplication(application);
								try {
									Thread.sleep(4000);
								} 
								catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								System.out.println("\nYour application is submitted successfully!");
								application.viewApplicationForm();
								break;
								
							}
							else
								application=null;
						}
						else if(option==2)
						{
						    application = null;
						    System.out.println("Payment canceled. Application not submitted.");
						}
						else
						{
							System.out.println("\nPayment canceled. Application not submitted.");
						}

					}
					else if(choice==2)
					{
					    Application application = new Application();

						System.out.println("\nPersonal, Academic, Course and Exam center location preference are added successfully!");

						Payment payment = null;
						System.out.println("Please submit the application by making Payment of Rs.910 -");


						System.out.println("______________________________________________________");			

						System.out.println("1.Make Payment\n2.Cancel and go back");
						System.out.print("Enter your choice: ");
						int option = sc.nextInt();

						System.out.println("______________________________________________________");			

						if(option == 1) {
							payment = runnerClass.makePayment(910, "Online", "CET Application Form");
						        							
							application.setStudent(student);

							PersonalDetails personalDetails = new PersonalDetails();
							application.setPersonalDetails(personalDetails);

							AcademicDetails academicDetails = new AcademicDetails();
							application.setAcademicDetails(academicDetails);

							CourseAndMarks courseAndMarks = new CourseAndMarks();
							application.setCourseAndMarks(courseAndMarks);

							ExamCenterLocationPreference examCenter = new ExamCenterLocationPreference();
							application.setExamCenterLocationPreference(examCenter);
							
							application.addPaymentDetails(payment);
	
						    applicationFormManager.createApplication(application);
						    System.out.println("\nYour application is submitted successfully!");
						    application.viewApplicationForm();
						    
						    break;
						}
						else if(option == 2) 
						{
						    application = null;
						    System.out.println("Payment canceled. Application not submitted.");
						} 
						else{
						    System.out.println("\nApplication not submitted.");
						}
						
					}
					
					else if(choice==3)
						return;
					else
						System.out.println("\nPlease choose a valid option!");
				}
			}
			else if(choice==2)
			{
				System.out.println("______________________________________________________");
				applicationFormManager.viewExistingForms(student);
			}
			
			else if(choice==3)
			{
				return;
			}
			else {
				System.out.println("______________________________________________________");

				System.out.println("\nPlease choose a valid option!");
			}
		}
		
	}
	
	public Payment makePayment(int amount, String paymentMode, String paymentFor) {
		Payment payment = new Payment(amount, paymentMode, paymentFor);
		System.out.println("\nPayment successfull!");
		System.out.println("payment details are given below -");
		System.out.println("Payment ID: "+payment.getPaymentId()+" Amount: "+payment.getAmount()+" DateTime: "+payment.getLdt()+" Payment mode: "+payment.getPaymentMode()+" Payment for: "+payment.getPaymentFor());
		return payment;
	}
}
