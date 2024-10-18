package com.galaxe.applicationForm;

import java.io.Serializable;
import java.util.HashMap;

public class CourseAndMarks implements Serializable{
	private static final long serialVersionUID = 1L;

	private HashMap<String, Integer>courseNameAndId;
	private HashMap<String, Float>marksObtainedMap;
	private HashMap<String, Integer>rankObtainedMap;
	
	
	public CourseAndMarks() {
		this.courseNameAndId = new HashMap<>();
		this.marksObtainedMap = new HashMap<>();
		this.rankObtainedMap = new HashMap<>();
		
		setCourseNameAndIdAuto();
	}
	private void setCourseNameAndIdAuto() {
		this.courseNameAndId.put("PCM", 10089);
	}
	
	public HashMap<String, Integer> getCourseNameAndId() {
		return courseNameAndId;
	}

	public void setCourseNameAndId(HashMap<String, Integer> courseNameAndId) {
		this.courseNameAndId = courseNameAndId;
	}

	public HashMap<String, Float> getMarksObtainedMap() {
		return marksObtainedMap;
	}

	public void setMarksObtainedMap(HashMap<String, Float> marksObtainedMap) {
		this.marksObtainedMap = marksObtainedMap;
	}

	public HashMap<String, Integer> getRankObtainedMap() {
		return rankObtainedMap;
	}

	public void setRankObtainedMap(HashMap<String, Integer> rankObtainedMap) {
		this.rankObtainedMap = rankObtainedMap;
	}
	
	public void displaySelectedCourse() {
		System.out.println("\nSeleted Course for CET Exam -");
		System.out.println(courseNameAndId);
	}
}
