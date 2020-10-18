package com.wf.apps.interviewTracker.service;

import java.util.List;

import com.wf.apps.interviewTracker.dto.InterviewCountDTO;
import com.wf.apps.interviewTracker.dto.InterviewDTO;

public interface InterviewService {

	public InterviewDTO addInterview(InterviewDTO interviewDto);
	public InterviewDTO deleteInterview(String technology);
	public InterviewDTO modifyStatus(String interviewName, String status);
	
	public boolean isAttendeeAddedToInterview(String interviewName,Integer userId);
	public List<InterviewDTO> getInterviewByInterviewer(String interviewerName);
	
	public InterviewDTO getInterview(String technology);
	public InterviewCountDTO getInterviewCount();
	public List<InterviewDTO> getAllInterviews();
	
	public InterviewDTO addAttendees(String interviewName,Integer userId);
	public InterviewDTO getAttendees(String interviewName);
		
}
