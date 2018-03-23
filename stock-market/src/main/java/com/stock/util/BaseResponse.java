package com.stock.util;

public class BaseResponse {
	
	int status;
	String message;
	public BaseResponse(int status) {
		this.status = status;
		this.message = ResponseMessages.messages.get(status);
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
