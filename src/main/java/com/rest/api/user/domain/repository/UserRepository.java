package com.rest.api.user.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.api.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
