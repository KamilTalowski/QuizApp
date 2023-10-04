package com.talowski.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.talowski.demo.model.Question;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer>{
	List<Question> findByDifficultyLevel(String difficultyLevel);
	
	@Query(value = "SELECT * FROM question q Where q.difficulty_level=:difficultyLevel ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)
	List<Question> findRandomQuestionByDifficultyLevel(String difficultyLevel, int numQ);
}
