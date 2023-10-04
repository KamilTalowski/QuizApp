package com.talowski.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.talowski.demo.dao.QuestionDao;
import com.talowski.demo.dao.QuizDao;
import com.talowski.demo.model.Question;
import com.talowski.demo.model.QuestionWrapper;
import com.talowski.demo.model.Quiz;
import com.talowski.demo.model.Response;

@Service
public class QuizService {
	@Autowired
	QuizDao quizDao;
	@Autowired
	QuestionDao questionDao;

	public ResponseEntity<String> createQuiz(String difficultyLevel, int numQ, String title) {
		
		List<Question> questions = questionDao.findRandomQuestionByDifficultyLevel(difficultyLevel, numQ);
		
		Quiz quiz = new Quiz();
		quiz.setTitlle(title);
		quiz.setQuestion(questions);
		quizDao.save(quiz);
		return new ResponseEntity<> ("Success", HttpStatus.CREATED);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizzQuesions(Integer id) {
		Optional<Quiz> quiz = Optional.of(quizDao.getReferenceById(id));
		List<Question> questionsFromDB = quiz.get().getQuestion();
		List<QuestionWrapper> questionsForUser = new ArrayList<>();
		for(Question q : questionsFromDB) {
			QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4(), q.getQuestionTitle());
			questionsForUser.add(qw);
		}
		
		return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
	}

	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
		Quiz quiz = quizDao.findById(id).get();
		List<Question> question = quiz.getQuestion();
		int right = 0;
		int i = 0;
		for(Response response :responses) {
			if(response.getResponse().equals(question.get(i).getCorrectAnswer()))
				right++;
			i++;
		}
		return new ResponseEntity<Integer>(right, HttpStatus.OK);
	}

}
