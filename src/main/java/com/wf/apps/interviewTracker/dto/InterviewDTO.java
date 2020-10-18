package com.wf.apps.interviewTracker.dto;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.wf.apps.interviewTracker.entity.UserEntity;

import lombok.Data;

@Data
public class InterviewDTO {	
	
	
	private Integer interviewId; 
	
	@NotBlank(message = "Interviewer Name shouldn't be blank!")
	@Length(min = 5, max = 30, message = "Interviewer Name Should be min 5 and max 30 characters!")
	private String interviewerName;
	
	@NotBlank(message = "Interview Name shouldn't be blank!")
	@Length(min = 3, max = 30, message = "Interview Name Should be min 3 and max 30 characters!")
	private String interviewName;

	private String userSkills;

	@NotBlank(message = "Interview Status shouldn't be blank!")
	@Length(min = 5, max = 100, message = "Interview Status Should be min 5 and max 100 characters!")
	private String interviewStatus;

	
	@NotBlank(message = "Remarks shouldn't be blank!")
	@Length(min = 5, max = 100, message = "Remarks Should be min 5 and max 100 characters!")
	private String remarks;
	
	private LocalTime time; 
	private LocalDate date; 
	private List<UserEntity> users;
}
