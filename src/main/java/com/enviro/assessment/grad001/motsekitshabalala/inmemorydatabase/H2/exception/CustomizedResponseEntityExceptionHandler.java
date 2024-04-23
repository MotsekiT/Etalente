package com.enviro.assessment.grad001.motsekitshabalala.inmemorydatabase.H2.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.enviro.assessment.grad001.motsekitshabalala.inmemorydatabase.H2.waste.WasteNotFoundException;



@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {


	@ExceptionHandler(Exception.class)
	// Indicates that this method handles exceptions of type Exception
	public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) throws Exception {
	    // Create an instance of ErrorDetails containing timestamp, error message, and request description
		ErrorDetails errorDetials = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
		// Return ResponseEntity with the ErrorDetails and HTTP status code INTERNAL_SERVER_ERROR
		return new ResponseEntity<>(errorDetials, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// Indicates that this method handles exceptions of type WasteNotFoundException
	@ExceptionHandler(WasteNotFoundException.class)
	public final ResponseEntity<ErrorDetails> handleUserNotFoundException(Exception ex, WebRequest request) throws Exception {
		// Create an instance of ErrorDetails containing timestamp, error message, and request description
		ErrorDetails errorDetials = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
		// Return ResponseEntity with the ErrorDetails and HTTP status code NOT_FOUND
		return new ResponseEntity<>(errorDetials, HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
	    // Create an error message detailing the number of errors and their descriptions
		ErrorDetails errorDetials = new ErrorDetails(LocalDateTime.now(),
				"Total Errors: " + ex.getErrorCount()+ " -> "+ " Error list: " + ex.getFieldError().getDefaultMessage(), request.getDescription(false));

		// Return ResponseEntity with the ErrorDetails and HTTP status code
		return new ResponseEntity(errorDetials, HttpStatus.BAD_REQUEST);

	}

}
