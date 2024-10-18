package com.galaxe.functionalities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import com.galaxe.actors.Admin;
import com.galaxe.actors.College;
import com.galaxe.actors.Student;

public class RegisterAndLogin{
	
	private String studentFileName;
	private String collegeFileName;
	private String adminFileName;
	
	private HashMap<String, Student>studentMap;
	private HashMap<String, Admin>adminMap;
		
	public HashMap<String, Student> getStudentMap() {
		return studentMap;
	}

	public void setStudentMap(HashMap<String, Student> studentMap) {
		this.studentMap = studentMap;
	}


	public RegisterAndLogin(){
		studentFileName = "StudentRegister.txt";
		collegeFileName = "CollegeRegister.txt";
		adminFileName = "AdminRegister.txt";
		
		studentMap = new HashMap<>();
		adminMap = new HashMap<>();
		
		fetchStudentDataFromFile();
		fetchAdminDataFromFile();
	}
	
	
	private File createDirectoryAndFile(String fileName) {
		String filePath="./files/";
		File directory = new File(filePath);
		directory.mkdir();
		File file = new File(directory, fileName);
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
	
	private boolean writeRegistrationData(File file, String registrationData) {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(file,true))){
			bw.write(registrationData);
			bw.newLine();
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	
	//Student registration and login
	public void registerStudent(Student student) {
		
		//Creating a registration.txt file
		File file = createDirectoryAndFile(this.studentFileName);
		
		String registrationData = "ID:"+student.getId()+",Name:"+student.getFullName()+",Email:"+student.getEmail()+",Password:"+student.getPassword();

		boolean isRegistered = writeRegistrationData(file, registrationData);
		
		studentMap.put(student.getEmail(), student);
		
		if(isRegistered)
			System.out.println("\nStudent is registered successfully!");
		else
			System.out.println("\nStudent registration failed");
	}
	
	public boolean studentLogin(String email, String password) {
		if(studentMap.containsKey(email))
		{
			Student student = studentMap.get(email);
			if(student.getPassword().equals(password))
			{
				System.out.println("\nHello "+student.getFullName()+", you are logged in!");
				return true;
			}
			
		}
		System.out.println("\nPlease enter correct Email ID or Password!");
		return false;
	}
	
	public void fetchStudentDataFromFile(){
		File file = createDirectoryAndFile(this.studentFileName);
		
		try(BufferedReader br = new BufferedReader(new FileReader(file)))
		{
			String line="";
			while((line=br.readLine())!=null)
			{
				String str[] = line.split(",");
				
				Student student = new Student(Integer.parseInt(str[0].substring(3)),str[1].substring(5), str[2].substring(6), str[3].substring(9));
				studentMap.put(student.getEmail(), student);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("\nUnable to fetch student data!");
		}
	}
	
	
	
	//College registration and Login
	public boolean writeCollegeObjectsToFile(File file, College college) {       
	    ArrayList<College> colleges = readCollegeObjectsFromFile();
	    colleges.add(college);

	    try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) 
	    {
	        for(College c : colleges) 
	        {
	            oos.writeObject(c);
	        }
	        oos.flush();
	        return true;
	    } 
	    catch(EOFException e)
	    {
	    	
	    }
	    catch(Exception e) 
	    {
	        e.printStackTrace();
	        System.out.println("\nUnable to write College details!");
	        return false;
	    }
	    return false;
	}

	public ArrayList<College> readCollegeObjectsFromFile() {
	    File file = createDirectoryAndFile(this.collegeFileName);
	    ArrayList<College> list = new ArrayList<>();
	    
	    try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
	        while(true) 
	        {
	            College college = (College) ois.readObject();
	            list.add(college);
	        }
	    }
	    catch(EOFException e1)
	    {
	    	
	    }
	    catch(Exception e) 
	    {
	        e.printStackTrace();
	        System.out.println("\nError occurred while fetching college details!");
	    }
	    
	    return list;
	}

	
	public void registerCollege(College college) {
		File file = createDirectoryAndFile(this.collegeFileName);
		
		boolean isRegistered = writeCollegeObjectsToFile(file, college);
		
		if(isRegistered)
		{
			System.out.println("\nCollege is registered successfully!");
			college.displayDetails();
		}
		else
			System.out.println("\nCollege registration is failed due to some reason!");
		
	}
	
	public boolean collegeLogin(int collegeID, String password) {
		
		ArrayList<College>list = readCollegeObjectsFromFile();
		for(College college: list)
		{
			if(college.getId()==collegeID && college.getPassword().equals(password))
			{
				System.out.println("\n"+college.getCollegeName()+" logged in successfully!");
				return true;
			}
		}
		System.out.println("\nPlease enter correct College ID or Password!");
		return false;
	}
	
	
	public void registerAdmin(Admin admin) {
		
		//Creating a AdminRegistration.txt file
		File file = createDirectoryAndFile(this.adminFileName);
		
		String registrationData = "ID:"+admin.getId()+",Name:"+admin.getName()+",Email:"+admin.getEmail()+",Password:"+admin.getPassword();

		boolean isRegistered = writeRegistrationData(file, registrationData);
		adminMap.put(admin.getEmail(), admin);
				
		if(isRegistered)
			System.out.println("\nAdmin is registered successfully!");
		else
			System.out.println("\nAdmin registration failed");
	}
	
	public void fetchAdminDataFromFile(){
		File file = createDirectoryAndFile(this.adminFileName);
		
		try(BufferedReader br = new BufferedReader(new FileReader(file)))
		{
			String line="";
			while((line=br.readLine())!=null)
			{
				String str[] = line.split(",");
				
				Admin admin = new Admin(str[1].substring(5), str[2].substring(6), str[3].substring(9));
				adminMap.put(admin.getEmail(), admin);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("\nUnable to fetch admin data!");
		}
	}
	
	public boolean adminLogin(String email, String password) {
		if(adminMap.containsKey(email))
		{
			Admin admin = adminMap.get(email);
			if(admin.getPassword().equals(password))
			{
				System.out.println("\nAdmin account - "+admin.getName()+", logged in successfully!");
				return true;
			}
		}
		System.out.println("\nPlease enter correct Email ID or Password!");
		return false;
	}
}
