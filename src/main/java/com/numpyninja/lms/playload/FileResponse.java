package com.numpyninja.lms.playload;

public class FileResponse {

	
	private String name;
	private String message;
	
	public FileResponse(Object object, String string) {
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public FileResponse(String name, String message) {
		super();
		this.name = name;
		this.message = message;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
