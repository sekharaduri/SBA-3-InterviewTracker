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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wf.apps.interviewTracker.dto.InterviewCountDTO;
import com.wf.apps.interviewTracker.dto.InterviewDTO;
import com.wf.apps.interviewTracker.exceptions.CustomExceptions;
import com.wf.apps.interviewTracker.service.InterviewService;
import com.wf.apps.interviewTracker.service.UserService;

@RestController
public class InterviewController {

	@Autowired
	UserService userservice;
	@Autowired
	InterviewService interviewservice;

	@PostMapping("/interviews")
	public ResponseEntity<InterviewDTO> addInterview(@Valid @RequestBody InterviewDTO interviewDTO,
			BindingResult result) {

		if (result.hasErrors()) {
			CustomExceptions exception = new CustomExceptions();
			for (FieldError err : result.getFieldErrors()) {
				if (exception.getErrorMessage() == null) {
					exception.setErrorCode(err.getCode());
					exception.setErrorMessage(err.getDefaultMessage());
				} else {
					exception.setErrorCode(exception.getErrorCode() + " || " + err.getCode());
					exception.setErrorMessage(exception.getErrorMessage() + " || " + err.getDefaultMessage());
				}
			}
			exception.setErrorTimeStamp(LocalDateTime.now());
			throw exception;
		}

		if(interviewDTO.getUserSkills() == null || interviewDTO.getUserSkills().isEmpty())
		{
			throw new CustomExceptions(LocalDateTime.now(),"User Skill Error","User Skills should'nt be null or empty or blank");
		}

		return new ResponseEntity<InterviewDTO>(interviewservice.addInterview(interviewDTO), HttpStatus.OK);
	}

	@GetMapping("/addAttendees/{interviewName}/{userId}")
	public ResponseEntity<InterviewDTO> addAttendees(@PathVariable("interviewName") String interviewName,
			@PathVariable("userId") Integer userId) {
		if (interviewservice.getInterview(interviewName) == null || userservice.getUserById(userId) == null)
			throw new CustomExceptions(LocalDateTime.now(), "Entity mapping Exception",
					"Inerview or user doesn't exist!");

		if (interviewservice.isAttendeeAddedToInterview(interviewName, userId))
			throw new CustomExceptions(LocalDateTime.now(), "Users Exception",
					"One or more of the user Ids in the request are already added to the " + interviewName
							+ " Interview.");

		return new ResponseEntity<InterviewDTO>(interviewservice.addAttendees(interviewName, userId), HttpStatus.OK);
	}

	/*
	 * @GetMapping("/addAttendees/{interviewName}/{userId}") public
	 * ResponseEntity<InterviewDTO> addAttendees(@PathVariable("interviewName")
	 * String interviewName,@PathVariable("userId") Integer userId) { //
	 * if(result.hasErrors()) // { // customExceptions exception=new
	 * customExceptions(); // for(FieldError err:result.getFieldErrors()) // { //
	 * if(exception.getErrorMessage()==null) { //
	 * exception.setErrorCode(err.getCode()); //
	 * exception.setErrorMessage(err.getDefaultMessage()); // } // else // { //
	 * exception.setErrorCode(exception.getErrorCode()+" || "+err.getCode()); //
	 * exception.setErrorMessage(exception.getErrorMessage()+" || "+err.
	 * getDefaultMessage()); // } // } //
	 * exception.setErrorTimeStamp(LocalDateTime.now()); // throw exception; // }
	 * if(interviewservice.getInterview(interviewName)==null ||
	 * userservice.getUserById(userId)==null) throw new
	 * CustomExceptions(LocalDateTime.now(),"Association Exception"
	 * ,"Inerview or user doesnt exist");
	 * 
	 * if(interviewservice.isAttendeeAddedToInterview(interviewName,userId)) throw
	 * new CustomExceptions(LocalDateTime.now(),"Users Exception"
	 * ,"One or more of the user Ids in the request are already added to the "
	 * +interviewName+" Interview"); return new
	 * ResponseEntity<InterviewDTO>(interviewservice.addAttendees(interviewName,
	 * userId),HttpStatus.OK); }
	 */
	  
	  
	@DeleteMapping("/removeInterview/{interviewName}")
	public ResponseEntity<InterviewDTO> removeInterview(@PathVariable("interviewName") String interviewName) {
		
		if(searchInterview(interviewName)==null)
			throw new CustomExceptions(LocalDateTime.now(),"Interview Exception", interviewName+" Interview already Deleted Or doesn't exist!");
		return new ResponseEntity<InterviewDTO>(interviewservice.deleteInterview(interviewName),HttpStatus.OK);
	}
	
	@PutMapping("/modifyStatus/{interviewName}/{status}")
	public ResponseEntity<InterviewDTO> modifyInterviewStatus(@PathVariable("interviewName") String interviewName,@PathVariable("status") String status)
	{
		InterviewDTO interview=interviewservice.getInterview(interviewName);
		if(interview==null)
			throw new CustomExceptions(LocalDateTime.now(),"Interview Exception","Interview doesn't exist!");
		return new ResponseEntity<InterviewDTO>(interviewservice.modifyStatus(interviewName,status),HttpStatus.OK);
	}
		
	@GetMapping("/searchInterviewByInterviewName/{interviewName}")
	public ResponseEntity<InterviewDTO> searchInterviewByName(@PathVariable("interviewName") String interviewName) {
		InterviewDTO interview = interviewservice.getInterview(interviewName);
		if (interview == null)
			throw new CustomExceptions(LocalDateTime.now(), "Interview Exception", "Interview doesn't exist!");

		return new ResponseEntity<InterviewDTO>(interview, HttpStatus.OK);
	}

	@GetMapping("/searchInterviewByInterviewerName/{interviewerName}")
	public ResponseEntity<List<InterviewDTO>> searchInterview(@PathVariable("interviewerName") String interviewerName) {
		List<InterviewDTO> interviews = interviewservice.getInterviewByInterviewer(interviewerName);
		if (interviews == null)
			throw new CustomExceptions(LocalDateTime.now(), "Interview Exception", "Interview doesn't exist!");

		return new ResponseEntity<List<InterviewDTO>>(interviews, HttpStatus.OK);
	}

	@GetMapping("/interviews")
	public ResponseEntity<List<InterviewDTO>> searchAllInterviews() {
		return new ResponseEntity<List<InterviewDTO>>(interviewservice.getAllInterviews(), HttpStatus.OK);
	}

	@GetMapping("/interviewCount")
	public ResponseEntity<InterviewCountDTO> getInterviewCount() {
		return new ResponseEntity<InterviewCountDTO>(interviewservice.getInterviewCount(), HttpStatus.OK);
	}

	/*
	 * @GetMapping("/getAttendeesByInterviewName/{interviewName}") public
	 * ResponseEntity<InterviewDTO> getAttendees(@PathVariable("interviewName")
	 * String intName) { if(interviewservice.getInterview(intName)==null) throw new
	 * CustomExceptions(LocalDateTime.now(),"Interview Exception"
	 * ,"Interview doesnt exist");
	 * 
	 * return new
	 * ResponseEntity<InterviewDTO>(interviewservice.getAttendees(intName),
	 * HttpStatus.OK); }
	 */
	@GetMapping("/getAttendeesByInterviewName/{name}")
	public ResponseEntity<InterviewDTO> getAttendees(@PathVariable("name") String intName)
	{
		if(interviewservice.getInterview(intName)==null)
			throw new CustomExceptions(LocalDateTime.now(),"Interview Exception","Interview doesnt exist");
		
		return new ResponseEntity<InterviewDTO>(interviewservice.getAttendees(intName),HttpStatus.OK);
	}
}
