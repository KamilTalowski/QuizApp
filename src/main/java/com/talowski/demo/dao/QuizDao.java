package com.talowski.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.talowski.demo.model.Quiz;

public interface QuizDao extends JpaRepository<Quiz, Integer>{
	
}
