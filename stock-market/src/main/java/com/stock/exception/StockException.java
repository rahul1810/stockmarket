package com.stock.exception;

import com.stock.util.ResponseMessages;

public class StockException extends Exception{
	int errorCode;
	
	public StockException(int errorCode) {
		super(ResponseMessages.messages.get(errorCode));
		this.errorCode = errorCode;
	}
	
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
}
