package com.example.detective;

public class EmailMessage {
	
	public static String getMess(String username,String userId) {
		
		String message="\r\n"
				+ "Hello "+username+",\r\n"
				+ "\r\n"
				+ "Thank you for joining your ID is"+userId+".\r\n"
				+ "\r\n"
				+ "We’d like to confirm that your account was created successfully.\r\n"
				+ "\r\n"
				+ "If you experience any issues logging into your account, reach out to us at bonsai@mailchain: \".\r\n"
				+ "\r\n"
				+ "Best,\r\n"
				+ "By Bonsay Cyber";
		
		return message;
		
	}
	public static void main(String[] args) {
		
	}

}
