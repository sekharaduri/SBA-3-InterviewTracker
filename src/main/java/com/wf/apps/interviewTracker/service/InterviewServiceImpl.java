package com.wf.apps.interviewTracker.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wf.apps.interviewTracker.dto.InterviewCountDTO;
import com.wf.apps.interviewTracker.dto.InterviewDTO;
import com.wf.apps.interviewTracker.entity.InterviewEntity;
import com.wf.apps.interviewTracker.entity.UserEntity;
import com.wf.apps.interviewTracker.repository.InterviewRepository;
import com.wf.apps.interviewTracker.repository.UserRepository;

@Service
@Transactional
public class InterviewServiceImpl implements InterviewService{
	@Autowired
	private InterviewRepository interviewRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public InterviewDTO addInterview(InterviewDTO interviewDto) {
		// TODO Auto-generated method stub
		
		InterviewEntity interview = new InterviewEntity();
		BeanUtils.copyProperties(interviewDto, interview);

		InterviewEntity interviewNew = this.interviewRepository.save(interview);

		BeanUtils.copyProperties(interviewNew, interviewDto);

		return interviewDto;
	}

	@Override
	public InterviewDTO deleteInterview(String technology) {
		// TODO Auto-generated method stub
		InterviewDTO interviewDto=getInterview(technology);
		interviewRepository.deleteByInterviewName(technology);
		
		return interviewDto;
	}

	@Override
	public InterviewDTO getInterview(String technology) {
		// TODO Auto-generated method stub	
			
		if(interviewRepository.findByInterviewName(technology)==null)
			return null;
		
		InterviewEntity interview = new InterviewEntity();
		interview = interviewRepository.findByInterviewName(technology);
		
		InterviewDTO interviewDto = new InterviewDTO();
		BeanUtils.copyProperties(interview, interviewDto);
		
		
		return interviewDto;
	}
	
	@Override
	public InterviewDTO modifyStatus(String interviewName, String status) {
		// TODO Auto-generated method stub
		
		InterviewEntity interview=this.interviewRepository.findByInterviewName(interviewName);
		interview.setInterviewStatus(status);
		
		InterviewDTO interviewDto = new InterviewDTO();
		BeanUtils.copyProperties(interview, interviewDto);
		
		return interviewDto;
	}

	@Override
	public boolean isAttendeeAddedToInterview(String interviewName, Integer userId) {
		// TODO Auto-generated method stub
			
		InterviewEntity interview=interviewRepository.findByInterviewName(interviewName);
		boolean found=false;
		
		if(interview!=null)
		{
			List<UserEntity> users=interview.getUsers();
			for(UserEntity user:users) {
					if(userId==user.getUserId())
						found=true;				
			}
				
		}
		return found;
	}

	@Override
	public List<InterviewDTO> getInterviewByInterviewer(String interviewerName) {
		// TODO Auto-generated method stub
		
		List<InterviewDTO> lsinterviewDto = new ArrayList<InterviewDTO>();
		
		
		if(interviewRepository.findAllByInterviewerName(interviewerName)==null)
			return null;
		
		InterviewDTO interviewDTO=new InterviewDTO();
		for (InterviewEntity interview:this.interviewRepository.findAllByInterviewerName(interviewerName)) {
			//InterviewDTO interviewDTO = new InterviewDTO();
			BeanUtils.copyProperties(interview, interviewDTO);
				
			lsinterviewDto.add(interviewDTO);
		}
		return lsinterviewDto;
	}

	

	@Override
	public InterviewCountDTO getInterviewCount() {
		// TODO Auto-generated method stub
		InterviewCountDTO countDto=new InterviewCountDTO();
		countDto.setNoOfInterviews(interviewRepository.interviewCount());
		return countDto;
	}

	@Override
	public List<InterviewDTO> getAllInterviews() {
		// TODO Auto-generated method stub
				
		List<InterviewDTO> lsinterviewDto = new ArrayList<InterviewDTO>();
		
		for (InterviewEntity interview:this.interviewRepository.findAll()) {
			InterviewDTO interviewDTO = new InterviewDTO();
			BeanUtils.copyProperties(interview, interviewDTO);
			lsinterviewDto.add(interviewDTO);
		}
		return lsinterviewDto;
	}

	
	/*
	 * @Override public InterviewDTO addAttendees(String interviewName, Integer
	 * userId) { // TODO Auto-generated method stub // UserEntity user;
	 * InterviewEntity InterviewEntity interviewEntity=
	 * interviewRepository.findByInterviewName(interviewName); List<UserEntity>
	 * interviewers = interviewEntity.getUsers();
	 * 
	 * if (interviewers == null) { interviewers = new ArrayList<UserEntity>(); user
	 * = userRepository.findById(userId).orElse(null); interviewers.add(user); }
	 * 
	 * interview.setUsers(interviewers); interviewRepository.save(interview);
	 * 
	 * InterviewDTO interviewDTO = new InterviewDTO();
	 * BeanUtils.copyProperties(interview, interviewDTO);
	 * 
	 * return interviewDTO; }
	 */
	
	@Override
	public InterviewDTO addAttendees(String interviewName,Integer userId) {
		// TODO Auto-generated method stub
		UserEntity user;		
		InterviewEntity interview=interviewRepository.findByInterviewName(interviewName);
		List<UserEntity> interviewusers=interview.getUsers();
		if(interviewusers==null)
			interviewusers=new ArrayList<UserEntity>();
			user=userRepository.findById(userId).orElse(null);
			interviewusers.add(user);
		interview.setUsers(interviewusers);
		interviewRepository.save(interview);
		
		InterviewDTO interviewDTO = new InterviewDTO();
		BeanUtils.copyProperties(interview, interviewDTO);
		
		return interviewDTO;
	}
	
	@Override
	public InterviewDTO getAttendees(String interviewName) {
		// TODO Auto-generated method stub

		InterviewDTO interviewDTO = new InterviewDTO();
		BeanUtils.copyProperties(interviewRepository.findByInterviewName(interviewName), interviewDTO);

		return interviewDTO;
	}
 
	/*
	 * @Override public InterviewDTO getAttendees(String intName) { // TODO
	 * Auto-generated method stub return
	 * interviewConvertor.interviewToInterviewDtoConvertor(interviewRepository.
	 * findByInterviewName(intName)); }
	 */
}
