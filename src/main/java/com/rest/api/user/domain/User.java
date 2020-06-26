package com.rest.api.user.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Entity
@Table(name = "TBL_USER")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_seq")
	private Long userSeq;
	
	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "user_password")
	private String userPassword;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "user_email")
	private String userEmail;

	public User() {
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public void updateUser(User updateUser) {
		this.userName = updateUser.userName;
		this.userEmail = updateUser.userEmail;
	}
}
