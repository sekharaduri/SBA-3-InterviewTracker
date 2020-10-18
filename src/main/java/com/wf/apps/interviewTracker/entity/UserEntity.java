package com.wf.apps.interviewTracker.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_table")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "userId")
public class UserEntity {
	
	@Id  // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId; 
	private String firstName;
	private String lastName; 
	private String email;
	private String mobile;

	@ManyToMany(mappedBy = "users")
	List<InterviewEntity> interviews =new ArrayList<InterviewEntity>();
}
