package com.StudentCourse.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalException {

	
	@ExceptionHandler(OtherException.class)
	public ResponseEntity<ErrorFormatDetails> otherHand(OtherException se, WebRequest req) {

		ErrorFormatDetails err = new ErrorFormatDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(se.getMessage());
		err.setDetails(req.getDescription(false));

		return new ResponseEntity<ErrorFormatDetails>(err, HttpStatus.NOT_FOUND);

	}


	@ExceptionHandler(studentexception.class)
	public ResponseEntity<ErrorFormatDetails> userHand(studentexception se, WebRequest req) {

		ErrorFormatDetails err = new ErrorFormatDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(se.getMessage());
		err.setDetails(req.getDescription(false));

		return new ResponseEntity<ErrorFormatDetails>(err, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorFormatDetails> rollback(MethodArgumentNotValidException se) {

		ErrorFormatDetails err = new ErrorFormatDetails(LocalDateTime.now(), "Validation Error",
				se.getBindingResult().getFieldError().getDefaultMessage());

		return new ResponseEntity<ErrorFormatDetails>(err, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrorFormatDetails> NoExceptionHandler(NoHandlerFoundException se, WebRequest req) {

		ErrorFormatDetails err = new ErrorFormatDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(se.getMessage());
		err.setDetails(req.getDescription(false));

		return new ResponseEntity<ErrorFormatDetails>(err, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorFormatDetails> otherExceptionHandler(Exception se, WebRequest req) {

		ErrorFormatDetails err = new ErrorFormatDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(se.getMessage());
		err.setDetails(req.getDescription(false));

		return new ResponseEntity<ErrorFormatDetails>(err, HttpStatus.BAD_REQUEST);

	}
}
