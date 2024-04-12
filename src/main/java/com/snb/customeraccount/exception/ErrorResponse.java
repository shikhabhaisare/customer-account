package com.snb.customeraccount.exception;


import org.springframework.http.HttpStatus;

import java.util.List;

public class ErrorResponse {

    private HttpStatus status;

    private String message;

    private List<String> details;

	/**
	 * @param status
	 * @param message
	 * @param details
	 */
	public ErrorResponse(HttpStatus status, String message, List<String> details) {
		super();
		this.status = status;
		this.message = message;
		this.details = details;
	}

	/**
	 * @return the status
	 */
	public HttpStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the details
	 */
	public List<String> getDetails() {
		return details;
	}

	/**
	 * @param details the details to set
	 */
	public void setDetails(List<String> details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "ErrorResponse [status=" + status + ", message=" + message + ", details=" + details + "]";
	}
    
    

}