package com.ivanjermakov;

public class User {
	
	public enum Progress {
		INIT,
		READY,
		TASK1,
		TASK2,
		TASK3,
		TASK4,
		END
	}
	
	private String userName;
	private Progress progress;
	
	public String getUserName() {
		return userName;
	}
	
	public Progress getProgress() {
		return progress;
	}
	
	public void setProgress(Progress progress) {
		this.progress = progress;
	}
	
	public User(String userName) {
		this.userName = userName;
		this.progress = Progress.INIT;
	}
}
