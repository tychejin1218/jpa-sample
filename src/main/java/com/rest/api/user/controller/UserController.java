package com.rest.api.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.api.user.domain.User;
import com.rest.api.user.domain.service.UserService;

@RequestMapping("/v1/user")
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * 회원 - 목록 조회
	 * 
	 * @return
	 */
	@GetMapping(value = "", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<User>> getUserList() {

		List<User> userList = userService.getUserList();

		return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
	}

	/**
	 * 회원 - 상세 조회
	 * 
	 * @param boardSeq
	 * @return
	 */
	@GetMapping(value = "/{userSeq}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<User> getUser(@PathVariable("userSeq") Long userSeq) {

		User user = userService.getUser(userSeq);

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	/**
	 * 회원 - 저장
	 * 
	 * @param insertUser
	 * @return
	 */
	@PostMapping(value = "", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<User> insertUser(@RequestBody User insertUser) {

		User user = userService.insertUser(insertUser);

		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}

	/**
	 * 회원 - 수정
	 * 
	 * @param userSeq
	 * @param updateUser
	 * @return
	 */
	@PutMapping(value = "/{userSeq}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<User> updateUser(@PathVariable("userSeq") Long userSeq, @RequestBody User updateUser) {

		User user = userService.updateUser(userSeq, updateUser);

		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}
	
	/**
	 * 회원 - 삭제
	 * 
	 * @param userSeq
	 * @return
	 */
	@DeleteMapping(value = "/{userSeq}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Void> deleteUser(@PathVariable("userSeq") Long userSeq) {

		userService.deleteUser(userSeq);

		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}