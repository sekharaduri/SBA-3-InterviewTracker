package com.wf.apps.interviewTracker.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wf.apps.interviewTracker.dto.UserDTO;
import com.wf.apps.interviewTracker.exceptions.CustomExceptions;
import com.wf.apps.interviewTracker.service.InterviewService;
import com.wf.apps.interviewTracker.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userservice;
	@Autowired
	InterviewService interviewservice;

	@PostMapping("/users")
	public ResponseEntity<UserDTO> addUser(@Valid @RequestBody UserDTO userDto, BindingResult result) {

		/*
		 * if (result.hasErrors()) { CustomExceptions exception = new
		 * CustomExceptions(); for (FieldError err : result.getFieldErrors()) { if
		 * (exception.getErrorMessage() == null) {
		 * exception.setErrorCode(err.getCode());
		 * exception.setErrorMessage(err.getDefaultMessage()); } else {
		 * exception.setErrorCode(exception.getErrorCode() + " || " + err.getCode());
		 * exception.setErrorMessage(exception.getErrorMessage() + " || " +
		 * err.getDefaultMessage()); } }
		 * exception.setErrorTimeStamp(LocalDateTime.now()); throw exception; }
		 */
		return new ResponseEntity<UserDTO>(userservice.addUser(userDto),HttpStatus.OK);
	}

	@DeleteMapping("/users/{mobile}")
	public ResponseEntity<UserDTO> deleteUser(@PathVariable("mobile") String mobile) {
		if (!userservice.isUserPresent(mobile))
			throw new CustomExceptions(LocalDateTime.now(), "Users Exception",
					"User with Mobile " + mobile + " already Deleted or user doesn't exist");
		return new ResponseEntity<UserDTO>(userservice.deleteUser(mobile), HttpStatus.OK);
	}

	@GetMapping("/users")
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		return new ResponseEntity<List<UserDTO>>(userservice.listAllUsers(), HttpStatus.OK);
	}
}
