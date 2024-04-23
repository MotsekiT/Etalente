package com.enviro.assessment.grad001.motsekitshabalala.inmemorydatabase.H2.waste;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//Indicates that instances of this class will be associated with the HTTP status code NOT_FOUND
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class WasteNotFoundException extends RuntimeException {

	public WasteNotFoundException(String message) {
		super(message);
	}
}
