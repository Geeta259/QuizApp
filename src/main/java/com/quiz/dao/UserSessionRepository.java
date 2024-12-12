package com.quiz.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quiz.entities.UserSession;

public interface UserSessionRepository extends JpaRepository<UserSession, Integer>{
	UserSession findById(int id);
}
