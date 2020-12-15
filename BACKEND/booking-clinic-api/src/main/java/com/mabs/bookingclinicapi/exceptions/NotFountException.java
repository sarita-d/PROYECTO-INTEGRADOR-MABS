package com.mabs.bookingclinicapi.exceptions;

import java.util.Arrays;

import org.springframework.http.HttpStatus;

import com.mabs.bookingclinicapi.dtos.ErrorDto;

public class NotFountException extends BookingException{

	private static final long serialVersionUID = 1L;

	public NotFountException(String code, String message) {
		super(code,HttpStatus.NOT_FOUND.value(),message);
	}
	
	public NotFountException(String code, String message, ErrorDto data) {
		super(code,HttpStatus.NOT_FOUND.value(),message, Arrays.asList(data));
	}

}
