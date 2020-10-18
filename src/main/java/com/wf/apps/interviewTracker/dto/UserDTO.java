package com.wf.apps.interviewTracker.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class UserDTO {

	private Integer userId;
	@NotBlank(message = "First Name shouldn't be blank!")
	@Length(min = 5, max = 30, message = "First Name Should be min 5 and max 30 characters!")
	private String firstName;
	@NotBlank(message = "Last Name shouldn't be blank!")
	@Length(min = 5, max = 25, message = "First Name Should be min 5 and max 30 characters!")
	private String lastName;
	@NotBlank(message = "Email shouldn't be blank")
	@Email(message = "Enter valid email id!")
	private String email;
	@NotBlank(message = "Mobile number shouldn't be blank")
	@Length(min = 10, max = 10, message = "Mobile number should of 10 digits!")
	private String mobile;
}
