package com.enviro.assessment.grad001.motsekitshabalala.inmemorydatabase.H2.exception;

import java.time.LocalDateTime;

public class ErrorDetails {

	private LocalDateTime timestampDate;
	private String message;
	private String details;

	public ErrorDetails(LocalDateTime timestampDate, String message, String details) {
		super();
		this.timestampDate = timestampDate;
		this.message = message;
		this.details = details;
	}

	public LocalDateTime getTimestampDate() {
		return timestampDate;
	}


	public String getMessage() {
		return message;
	}


	public String getDetails() {
		return details;
	}






}
