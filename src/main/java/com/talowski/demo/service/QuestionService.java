package com.talowski.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.talowski.demo.dao.QuestionDao;
import com.talowski.demo.model.Question;

@Service
public class QuestionService {
	@Autowired
	QuestionDao questionDao;
	
	public ResponseEntity<List<Question>> getAllQuestions() {
		List<Question> que;
		try {
		que=(List<Question>)questionDao.findAll();
		System.out.println(que);
		return new ResponseEntity<>(que, HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<List<Question>> getQuestionsBydifficulty(String difficultyLevel) {
		try {
			return new ResponseEntity<>(questionDao.findByDifficultyLevel(difficultyLevel), HttpStatus.OK);
			}catch(Exception e) {
				e.printStackTrace();
			}return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<String> addQuestion(Question question) {
		try {
			questionDao.save(question);
			return new ResponseEntity<String>("success", HttpStatus.CREATED);
			}catch(Exception e) {
				e.printStackTrace();
			}return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<String> deleteQuestion(Integer id) {
		try {
			questionDao.deleteById(id);
			return new ResponseEntity<String>("success", HttpStatus.OK);
			}catch(Exception e) {
				e.printStackTrace();
			}return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
	}

}
