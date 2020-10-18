package com.wf.apps.interviewTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wf.apps.interviewTracker.dto.UserDTO;
import com.wf.apps.interviewTracker.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>{

public Integer deleteByMobile(String mobile);
public UserEntity findByMobile(String mobile);

}
