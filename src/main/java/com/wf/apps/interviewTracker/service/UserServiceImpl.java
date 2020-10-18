package com.wf.apps.interviewTracker.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wf.apps.interviewTracker.dto.UserDTO;
import com.wf.apps.interviewTracker.entity.UserEntity;
import com.wf.apps.interviewTracker.repository.InterviewRepository;
import com.wf.apps.interviewTracker.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userrepository;
	@Autowired
	InterviewRepository interviewrepository;
	
	
	@Override
	public UserDTO addUser(UserDTO userDto) {
		// TODO Auto-generated method stub

		UserEntity user = new UserEntity();
		BeanUtils.copyProperties(userDto, user);

		UserEntity userNew = this.userrepository.save(user);

		BeanUtils.copyProperties(userNew, userDto);

		return userDto;
	}

	/*
	 * @Override public UserDTO addUser(UserDTO userDto) { // TODO Auto-generated
	 * method stub UserEntity user=UserConvertor.userDtoToUserConverted(userDto);
	 * return UserConvertor.userToUserDtoConverter(userrepository.save(user)); }
	 */
	
	@Override
	public UserDTO deleteUser(String mobile) {
		// TODO Auto-generated method stub

		UserEntity userEntity = this.userrepository.findByMobile(mobile);

		if (userEntity == null) {
			return null;
		}

		this.userrepository.deleteByMobile(mobile);

		UserDTO userDto = new UserDTO();
		
		BeanUtils.copyProperties(userEntity, userDto);

		return userDto;

	}

	@Override
	public List<UserDTO> listAllUsers() {
		// TODO Auto-generated method stub
		List<UserDTO> lsDto = new ArrayList<UserDTO>();
				
		for (UserEntity user:this.userrepository.findAll()) {
			UserDTO userDTO = new UserDTO();
			BeanUtils.copyProperties(user, userDTO);
			lsDto.add(userDTO);
		}
		
		return lsDto;
	}

	@Override
	public UserDTO getUserById(Integer userID) {
		// TODO Auto-generated method stub
		UserEntity userEntity = this.userrepository.findById(userID).orElse(null);

		UserDTO userDto = new UserDTO();
		BeanUtils.copyProperties(userEntity, userDto);

		return userDto;
	}

	@Override
	public boolean isUserPresent(String mobile) {
		// TODO Auto-generated method stub
		
		if(userrepository.findByMobile(mobile)==null)
			return false;
		return true;
	}

}
