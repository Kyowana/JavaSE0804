package com.edu.seiryo;

public class StudentInfo {
	
	String num;
	
	String name;
	
	String gender;
	
	String age;
	
	String classes;

	public StudentInfo(String i, String string, String string2, String j, String k) {
		this.num = i;
		this.name = string;
		this.gender = string2;
		this.age = j;
		this.classes = k;
	}
	
	public void printInfo(StudentInfo info) {
		System.out.println(info.num + "\t" + info.name + "\t" + info.gender + "\t" + info.age + "\t" + info.classes);
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getClasses() {
		return classes;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}
	

	
	

}
