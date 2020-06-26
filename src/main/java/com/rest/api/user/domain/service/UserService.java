package com.rest.api.user.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.api.user.domain.User;
import com.rest.api.user.domain.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	/**
	 * 회원 - 목록 조회
	 * 
	 * @return user
	 */
	public List<User> getUserList() {

		return userRepository.findAll();
	}
	
	/**
	 * 회원 - 상세 조회
	 * 
	 * @param userSeq
	 * @return
	 */
	public User getUser(Long userSeq) {

		User user = new User();

		Optional<User> userOptional = userRepository.findById(userSeq);
		if (userOptional.isPresent()) {
			user = userOptional.get();
		} 

		return user;
	}
	
	
	/**
	 * 회원 - 저장
	 *
	 * @param user
	 */
	public User insertUser(User insertUser) {

		return userRepository.save(insertUser);
	}
	
	/**
	 * 회원 - 수정
	 * 
	 * @param userSeq
	 * @param user
	 */
	public User updateUser(Long userSeq, User updateUser) {

		User user = new User();

		Optional<User> userOptional = userRepository.findById(userSeq);
		if (userOptional.isPresent()) {
			user = userOptional.get();
		}

		user.updateUser(updateUser);

		return userRepository.save(user);
	}
	
	/**
	 * 회원 - 삭제
	 * 
	 * @param userSeq
	 */
	public void deleteUser(Long userSeq) {

		userRepository.deleteById(userSeq);
	}
}
