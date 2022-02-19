package com.smart.helper;

public class Message {

private String text;
private String error;


public Message(String text, String error) {
	super();
	this.text = text;
	this.error = error;
}


public Message() {
	super();
	// TODO Auto-generated constructor stub
}


public String getText() {
	return text;
}
public void setText(String text) {
	this.text = text;
}
public String getError() {
	return error;
}
public void setError(String error) {
	this.error = error;
}

	
}
