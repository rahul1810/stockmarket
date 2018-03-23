
package com.stock.exception;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

import com.stock.util.ResponseMessages;

//@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
@ResponseBody
public class StockException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
