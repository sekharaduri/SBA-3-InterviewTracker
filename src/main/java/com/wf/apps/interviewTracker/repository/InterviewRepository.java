package com.wf.apps.interviewTracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.wf.apps.interviewTracker.entity.InterviewEntity;

@Repository
public interface InterviewRepository extends JpaRepository<InterviewEntity, Integer>{

	public InterviewEntity findByInterviewName(String technology);
	public List<InterviewEntity> findAllByInterviewerName(String name);
	public Integer deleteByInterviewName(String technology);
	@Query("SELECT COUNT(x) from InterviewEntity x")
	public Integer interviewCount();

}
