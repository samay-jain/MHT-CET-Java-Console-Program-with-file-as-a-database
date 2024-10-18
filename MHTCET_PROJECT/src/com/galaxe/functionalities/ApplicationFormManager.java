package com.galaxe.functionalities;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import com.galaxe.actors.College;
import com.galaxe.actors.Student;
import com.galaxe.applicationForm.Application;

public class ApplicationFormManager {

	private String fileName = "Applications.txt";
	private HashMap<Student, HashSet<Application>>studentApplicationsMap;
	Application application;
	boolean isAdmitCardGenerated=true;
	boolean isScoreCardGenerated=true;
	boolean isCollegeAllocated=true;
	

	public ApplicationFormManager(){
		studentApplicationsMap = new HashMap<>();
		readApplicationObjectsFromFile();
	}
	
	
	private File createDirectoryAndFile() {
		String filePath="./files/";
		File directory = new File(filePath);
		directory.mkdir();
		File file = new File(directory, this.fileName);
		
		try 
		{
	        if(!file.exists()) 
	        {
	            file.createNewFile();
	        }
	    } 
		catch (Exception e) {
	        System.out.println("Error creating the file: " + e.getMessage());
	    }
		
		return file;
	}
	
	
	public boolean writeApplicationObjectToFile(Application application) {
	    ArrayList<Application> allApplications = readApplicationObjectsFromFile();
	    allApplications.add(application);

	    File file = createDirectoryAndFile();
	    try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) 
	    {
	        for(Application app : allApplications) {
	            oos.writeObject(app);
	        }
	        
	        HashSet<Application> applications = studentApplicationsMap.getOrDefault(application.getStudent(), new HashSet<>());
	        if(!applications.contains(application)) 
	        {
	            applications.add(application);
	            studentApplicationsMap.put(application.getStudent(), applications);
		        return true;
	        }
	    } 
	    catch(Exception e) 
	    {
	        System.out.println("\nUnable to write Application Data to file!");
	        e.printStackTrace();
	    }
	    return false;
	}

	
	public ArrayList<Application> readApplicationObjectsFromFile() {
	    File file = createDirectoryAndFile();
	    ArrayList<Application> applicationsList = new ArrayList<>();
	    
	    try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) 
	    {
	        while(true) 
	        {
	            try {
	                Application application = (Application) ois.readObject();
	                applicationsList.add(application);
	                
	                HashSet<Application> applications = studentApplicationsMap.getOrDefault(application.getStudent(), new HashSet<>());
	                applications.add(application);
	                studentApplicationsMap.put(application.getStudent(), applications);
	                
	                if(application.getRollNo()==0)
	                	this.isAdmitCardGenerated=false;
	                
	                if(application.getCourseAndMarks().getMarksObtainedMap().size()==0 || application.getCourseAndMarks().getRankObtainedMap().size()==0)
	                	this.isScoreCardGenerated = false;
	                
	                if(application.getAllocatedCollegeAndBranch().isBlank())
	                	this.isCollegeAllocated = false;
	            }
	            catch(EOFException e1)
	            {
	            	break;
	            }
	            catch(Exception e) 
	            {
	                break; 
	            }
	        }
	    } 
	    catch(EOFException e1)
        {
	    	
        }
	    catch (Exception e) {
	        System.out.println("\nError occurred while fetching application details!");
	        e.printStackTrace();
	    }
	    return applicationsList;
	}

	
	
	public void createApplication(Application application)
	{
		writeApplicationObjectToFile(application);
	}
	
	public void viewExistingForms(Student student) {
		
		boolean isFound=false;
		ArrayList<Application>appList = readApplicationObjectsFromFile();
		
		for(Application app: appList)
		{
			if(app.getStudent().getId()==student.getId())
			{
				app.viewApplicationForm();
				isFound=true;
				System.out.println("______________________________________________________");			
			}
		}
		
		if(!isFound)
			System.out.println("\nNo application found!");
	}
	
	public void writeApplicationToFile(ArrayList<Application>appList) {

	    File file = createDirectoryAndFile();
	    try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) 
	    {
	        for(Application app : appList) {
	            oos.writeObject(app);
	        }
	    } 
	    catch(Exception e) 
	    {
	        System.out.println("\nUnable to write Application Data to file!");
	        e.printStackTrace();
	    }
	}
	public Application getApplicationById(int applicationId) {
		ArrayList<Application>appList = readApplicationObjectsFromFile();

		for(Application app: appList)
		{
			if(app.getApplicationId()==applicationId)
			{
				return app;
			}
		}
		return null;
	}
	
	
	public boolean generateAdmitCard() {
		
		if(this.isAdmitCardGenerated == true)
		{
			return false;
		}
		ArrayList<Application>appList = readApplicationObjectsFromFile();

		Random random = new Random();
		int min=10000000, max=99999999;
		int rollNo = random.nextInt(max-min)+min;
		
		for(Application app: appList)
		{
			app.setRollNo(rollNo);
		}
		writeApplicationToFile(appList);
		this.isAdmitCardGenerated = true;
		
		return true;
	}
	
	public ArrayList<List<Application>> getListOfSortedApplications(){
		
		ArrayList<List<Application>>listOfSortedApplications = new ArrayList<>();
		
		ArrayList<Application>appList = readApplicationObjectsFromFile();
		
		try {
			
			//PCM
			List<Application>sortedScoreListPCM = appList.stream().filter(app->app.getCourseAndMarks().getMarksObtainedMap().containsKey("PCM")).sorted((app1, app2)->{
				float marks1 = app1.getCourseAndMarks().getMarksObtainedMap().get("PCM");
				float marks2 = app2.getCourseAndMarks().getMarksObtainedMap().get("PCM");
						
				return Float.compare(marks2, marks1);
			}).collect(Collectors.toList());
			
			listOfSortedApplications.add(sortedScoreListPCM);
			
			//PCB
			List<Application>sortedScoreListPCB = appList.stream().filter(app->app.getCourseAndMarks().getMarksObtainedMap().containsKey("PCB")).sorted((app1,app2)->{
				float marks1 = app1.getCourseAndMarks().getMarksObtainedMap().get("PCB");
				float marks2 = app2.getCourseAndMarks().getMarksObtainedMap().get("PCB");
						
				return Float.compare(marks2, marks1);
			}).collect(Collectors.toList());
			
			listOfSortedApplications.add(sortedScoreListPCB);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return listOfSortedApplications;
	}
	
	
	public boolean generateScoreCard() 
	{
		if(this.isAdmitCardGenerated==false)
		{
			System.out.println("\nPlease wait for Admit Cards to be generated!");
			return false;
		}
		else if(this.isScoreCardGenerated==true)
		{
			System.out.println("\nScore cards are already generated!");
			return false;
		}
		
		ArrayList<Application>appList = new ArrayList<>();
		appList = readApplicationObjectsFromFile();
		Random random = new Random();
		int min=1, max=200;
		
		for(Application app: appList)
		{
			HashMap<String, Integer>course = app.getCourseAndMarks().getCourseNameAndId();
			HashMap<String, Float>marks = app.getCourseAndMarks().getMarksObtainedMap();			
			
			for(String courseName: course.keySet())
			{
				marks.put(courseName, (random.nextFloat(max-min)+min+100));
			}
			app.getCourseAndMarks().setMarksObtainedMap(marks);
		}
		writeApplicationToFile(appList);
		
		
		ArrayList<Application>appListToWriteBack = new ArrayList<>();
		
		ArrayList<List<Application>>listOfSortedApplications = new ArrayList<>();
		listOfSortedApplications = getListOfSortedApplications();		
		
		try {
			
			//PCM
			int rank=1;
			for(Application app: listOfSortedApplications.get(0))
			{
				app.getCourseAndMarks().getRankObtainedMap().put("PCM", rank);
				rank+=1;
				appListToWriteBack.add(app);
			}
			
			
			//PCB
			rank=1;
			for(Application app: listOfSortedApplications.get(1))
			{
				app.getCourseAndMarks().getRankObtainedMap().put("PCB", rank);
				rank+=1;
				appListToWriteBack.add(app);
			}	
			
			writeApplicationToFile(appListToWriteBack);
			this.isScoreCardGenerated = true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return true;
	}
	
	
	public void viewAdmitCard(int applicationId) {		
		ArrayList<Application>appList = readApplicationObjectsFromFile();
		
		for(Application app: appList)
		{
			if(app.getApplicationId()==applicationId && app.getRollNo()>0)
			{
				System.out.println("______________________________________________________");
				System.out.println("\nYour Admit Card Details for application ID - "+applicationId);
				System.out.println("Application ID: "+app.getApplicationId()+" RollNo: "+app.getRollNo());
				break;
			}
			if(app.getRollNo()==0)
			{
				System.out.println("\nAdmit cards are not yet generated! ");
				break;
			}
		}
	}

	public void viewScoreCard(int applicationId) {		
		Application app = getApplicationById(applicationId);
		
		if(app==null)
			System.out.println("\nPlease enter a valid application Id!");
		else
		{
			if(app.getCourseAndMarks().getRankObtainedMap().size()==0)
			{
				System.out.println("\nResults are not yet generated! ");
				return;
			}
			if(app.getApplicationId()==applicationId)
			{
				HashMap<String, Float>marks = app.getCourseAndMarks().getMarksObtainedMap();
				HashMap<String, Integer>ranks = app.getCourseAndMarks().getRankObtainedMap();
				
				System.out.println("______________________________________________________");
				System.out.println("\nMarks Obtained -");				
				for(String courseName: marks.keySet())
				{
					System.out.println("Course name: "+courseName+" Marks Obtained: "+marks.get(courseName));
				}
				System.out.println("\nRank obtained -");
				for(String courseName: ranks.keySet())
				{
					System.out.println("Course name: "+courseName+" Rank Obtained: "+ranks.get(courseName));
				}
			}
		}		
	}
	
	public boolean isCollegePreferenceAdded(int applicationId)
	{
		Application app = getApplicationById(applicationId);
		if(app==null)
			System.out.println("\nPlease enter a valid application Id!");
		else
		{
			if(app.getClgPreferenceMap()!=null && app.getClgPreferenceMap().size()>0)
				return true;
		}
		return false;
	}
	
	public void viewCollegePreferences(int applicationId) 
	{
		Application app = getApplicationById(applicationId);
		
		if(app==null)
			System.out.println("\nPlease enter a valid application Id!");
		else
		{
			System.out.println("\nYour preferred college and branch are -");
			for(Map.Entry<String, String> kv: app.getClgPreferenceMap().entrySet())
				System.out.println("Priority: "+kv.getKey().split("\\.")[0]+" College: "+kv.getKey().split("\\.")[1]+" - "+kv.getValue());
		}
	}
	
	public void addCollegePreferences(LinkedHashMap<String, String>prefMap, int applicationId)
	{
		ArrayList<Application>appList = readApplicationObjectsFromFile();
		for(Application app: appList)
		{
			if(app.getApplicationId()==applicationId)
			{
				app.setClgPreferenceMap(prefMap);
				break;
			}
		}
		writeApplicationToFile(appList);
	}
	
	public void allocateCollegeBasedOnRanking(RegisterAndLogin registerAndLogin) {
		
		if(this.isScoreCardGenerated==false)
		{
			System.out.println("\nPlease wait for Results to release!");
			return;
		}
		
		ArrayList<List<Application>>sortedAppList = new ArrayList<>();
		sortedAppList = getListOfSortedApplications();
		
//		if(this.isCollegeAllocated)
//		{
//			displayAllCollegeAllocation(sortedAppList);
//			return;
//		}
		
		ArrayList<Application>applicationsToWriteBack = new ArrayList<>();
		
		ArrayList<College>collegeList = new ArrayList<>();
		collegeList = registerAndLogin.readCollegeObjectsFromFile();
		
		//Seat/College allocation for PCM
		for(Application application: sortedAppList.get(0))
		{
			boolean isAllocated = false;
			for(Map.Entry<String, String>clgNameBranch: application.getClgPreferenceMap().entrySet())
			{
				isAllocated = false;
				for(College college: collegeList)
				{				
					if(college.getCollegeName().equals(clgNameBranch.getKey().split("\\.")[1]) && college.getBranchAndSeats().get(clgNameBranch.getValue())>0)
					{
						
						application.setAllocatedCollegeAndBranch("PrefNo: "+clgNameBranch.getKey().split("\\.")[0]+" College: "+college.getCollegeName()+" - "+clgNameBranch.getValue());
						int seatsRemaining = college.getBranchAndSeats().get(clgNameBranch.getValue());
						seatsRemaining -=1 ;
						college.getBranchAndSeats().put(clgNameBranch.getValue(), seatsRemaining);
						isAllocated = true;
						
						break;
					}
				}
				if(isAllocated)
					break;
			}
			if(!isAllocated)
				application.setAllocatedCollegeAndBranch("No college allocated!\nPlease try for next round.");
		}
		
		//Seat/College allocation for PCB
		for(Application application: sortedAppList.get(1))
		{
			boolean isAllocated = false;

			for(Map.Entry<String, String>clgNameBranch: application.getClgPreferenceMap().entrySet())
			{
				isAllocated = false;
				for(College college: collegeList)
				{
					if(college.getCollegeName().equals(clgNameBranch.getKey().split("\\.")[1]) && college.getBranchAndSeats().get(clgNameBranch.getValue())>0)
					{
						application.setAllocatedCollegeAndBranch("PrefNo: "+clgNameBranch.getKey().split("\\.")[0]+" College: "+college.getCollegeName()+" - "+clgNameBranch.getValue());
						int seatsRemaining = college.getBranchAndSeats().get(clgNameBranch.getValue());
						seatsRemaining -=1 ;
						college.getBranchAndSeats().put(clgNameBranch.getValue(), seatsRemaining);
						isAllocated = true;
						break;
					}
				}
				if(isAllocated)
					break;
			}
			if(!isAllocated)
				application.setAllocatedCollegeAndBranch("No college allocated!\nPlease try for next round.");
		}
		
		
		for(int i=0;i<sortedAppList.size();i++)
		{
			List<Application>list = sortedAppList.get(i);
			for(Application app: list)
				applicationsToWriteBack.add(app);
		}
		
		writeApplicationToFile(applicationsToWriteBack);
		
		displayAllCollegeAllocation(sortedAppList);
		this.isCollegeAllocated = true;
	}


	private void displayAllCollegeAllocation(ArrayList<List<Application>> sortedAppList) {
		System.out.println("\nAllocated Colleges for PCM -");
		for(Application app: sortedAppList.get(0))
		{
			app.displayAllocatedCollegeAndBranch();
		}
		
		System.out.println("\nAllocated Colleges for PCB -");
		for(Application app: sortedAppList.get(1))
		{
			app.displayAllocatedCollegeAndBranch();
		}
	}


	public void showCollegeAllocation(int applicationId) {
		Application app = getApplicationById(applicationId);
		if(app==null)
			System.out.println("\nPlease enter a valid application Id!");
		else
			if(this.isCollegeAllocated==true)
				System.out.println("\nAllocated Seat: "+app.getAllocatedCollegeAndBranch());
			else
				System.out.println("\nPlease wait for allocation results to be declared!");
			
	}
	
	public void displayAllCollegeDetails(RegisterAndLogin registerAndLogin){
		ArrayList<College>collegeList = new ArrayList<>();
		collegeList = registerAndLogin.readCollegeObjectsFromFile();
		
		if(collegeList.isEmpty())
		{
			System.out.println("\nNo college details to display!");
			return;
		}
		
		for(College clg:collegeList)
		{
			clg.displayDetails();
		}
	}
	
	
	public String getCourseNameByApplicationId(int applicationId) {
		String course="";
		
		Application app = getApplicationById(applicationId);
		if(app==null)
			System.out.println("\nPlease enter a valid application Id!");
		else
		{
			for(Map.Entry<String, Integer>kv: app.getCourseAndMarks().getCourseNameAndId().entrySet())
			{
				if(kv.getKey().equals("PCM"))
					course =  "Engineering";
				else if(kv.getKey().equals("PCB"))
					course = "Medical";
			}
		}
		return course;
	}
	
	public LinkedHashMap<String, String> displayCollegeAndBranchListForPreference(RegisterAndLogin registerAndLogin, int applicationId) {
		ArrayList<College>collegeList = new ArrayList<>();

		LinkedHashMap<String, String>collegeAndBranch = new LinkedHashMap<>();
		collegeList = registerAndLogin.readCollegeObjectsFromFile();
		String courseName = getCourseNameByApplicationId(applicationId);
		
		int count=1;
		for(College clg: collegeList)
		{						
			if(clg.getCourse().equals(courseName))
			{
				for(Map.Entry<String, Integer> kv: clg.getBranchAndSeats().entrySet())
				{
					System.out.println(count+"."+clg.getCollegeName()+";"+kv.getKey());
					
					collegeAndBranch.put(count+"."+clg.getCollegeName(), kv.getKey());
					count+=1;
				}
			}
		}
		return collegeAndBranch;
	}
	
	
	public void viewAllCollegeBranchAndSeats(RegisterAndLogin registerAndLogin) {
		ArrayList<College>collegeList = new ArrayList<>();
		collegeList = registerAndLogin.readCollegeObjectsFromFile();
		
		System.out.println("\nCollege - Branch - Seats\n");
		for(College college: collegeList)
		{
			System.out.println(college.getCollegeName()+" "+college.getBranchAndSeats());
		}
	}
	
	
	public HashMap<Student, HashSet<Application>> getStudentApplicationsMap() {
		return studentApplicationsMap;
	}


	public void setStudentApplicationsMap(HashMap<Student, HashSet<Application>> studentApplicationsMap) {
		this.studentApplicationsMap = studentApplicationsMap;
	}


	public Application getApplication() {
		return application;
	}


	public void setApplication(Application application) {
		this.application = application;
	}
}

