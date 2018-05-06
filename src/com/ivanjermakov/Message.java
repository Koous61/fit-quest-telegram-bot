package com.ivanjermakov;

import java.util.Date;

public class Message {
	
	private String text;
	private String userName;
	private int date;
	
	public Message(String text, String userName, int date) {
		this.text = text;
		this.userName = userName;
		this.date = date;
	}
	
	public String getText() {
		return text;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public int getDate() {
		return date;
	}
}
