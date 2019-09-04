package com.todo1.technicaltest.dto;

import java.io.Serializable;

public class BaseResponseDto implements Serializable {

	private static final long serialVersionUID = 13455654565726145L;
	
	private int statusCode;
	private String responseMessage;
	private Object responseBody;
	

	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	public Object getResponseBody() {
		return responseBody;
	}
	public void setResponseBody(Object responseBody) {
		this.responseBody = responseBody;
	}
	
}
