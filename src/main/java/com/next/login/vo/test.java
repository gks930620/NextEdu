package com.next.login.vo;

public class test {
 public static void main(String[] args) {
	String role = "STUDENT";
	String check = "STUDENT, TEACHER";
	String[] r = check.split(",");	
	System.out.println(check.contains(role));
	
	
}
}
