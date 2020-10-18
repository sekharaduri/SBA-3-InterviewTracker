package com.wf.apps.interviewTracker.service;

import java.util.List;

import com.wf.apps.interviewTracker.dto.UserDTO;
import com.wf.apps.interviewTracker.entity.UserEntity;


public interface UserService {

	public UserDTO addUser(UserDTO user);
	public UserDTO deleteUser(String id);
	public List<UserDTO> listAllUsers();
	public UserDTO getUserById(Integer id);
	public boolean isUserPresent(String mobile);
	
}
