package com.quiz.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.dao.QuestionRepository;
import com.quiz.dao.UserSessionRepository;
import com.quiz.entities.Question;
import com.quiz.entities.UserSession;

@RestController
@RequestMapping("/api/quiz")
public class MyController {

	 @Autowired
	    private QuestionRepository questionRepository;

	    @Autowired
	    private UserSessionRepository userSessionRepository;
	    
	    @PostMapping("/create")
	    public UserSession startSession() {
	    	UserSession user = new UserSession();
	        return userSessionRepository.save(user);
	    }
	    
	    @PostMapping("/create-question")
	    public Question createQuestion(@RequestBody Question question) {
	        if (question.getText() == null || question.getCorrect() == null || question.getOptions() == null || question.getOptions().isEmpty()) {
	            throw new RuntimeException("Invalid question data");
	        }
	        return questionRepository.save(question);
	    }
	    
	    
	    @GetMapping("/get-question/{userId}")
	    public ResponseEntity<Map<String, Object>> getQuestion(@PathVariable int userId) {
	        UserSession user = userSessionRepository.findById(userId);
	        if (user == null) {
	            throw new RuntimeException("No user available first create user");
	        }

	        List<Question> questions = questionRepository.findAll();
	        if (questions.isEmpty()) {
	            throw new RuntimeException("No questions available");
	        }

	       
	        Question question = questions.get(new Random().nextInt(questions.size()));

	       
	        Map<String, Object> response = new HashMap<>();
	        response.put("id", question.getQtnid());
	        response.put("question", question.getText());
	        response.put("options", question.getOptions());  
	        return ResponseEntity.ok(response);
	    }

	    @PostMapping("/answer")
	    public ResponseEntity<Map<String, Object>> submitAnswer(@RequestParam int id, @RequestParam int qtnid, @RequestParam String answer) {
	        UserSession user = userSessionRepository.findById(id);
	        if (user == null) {
	            throw new RuntimeException("No user available");
	        }

	        Question question = questionRepository.findByQtnId(qtnid);
	        if (question == null) {
	            throw new RuntimeException("No question found with ID: " + qtnid);
	        }

	         Map<String, Object> response = new HashMap<>();
	        response.put("question", question.getText());
	        response.put("options", question.getOptions());
	        response.put("userTotal", user.getTotal() + 1); 
	        user.setTotal(user.getTotal() + 1);

	        if (question.getCorrect().equals(answer)) {
	            user.setCorrect(user.getCorrect() + 1);
	            response.put("result", "Correct answer!");
	           
	        } else {
	            user.setIncorrect(user.getIncorrect() + 1);
	            response.put("result", "Incorrect answer.");
	           
	        }
	        response.put("correctAnswers", user.getCorrect());
	        response.put("incorrectAnswers", user.getIncorrect());
	        userSessionRepository.save(user);

	         return ResponseEntity.ok(response);
	    }

	    @PutMapping("/update-question/{questionId}")
	    public ResponseEntity<Question> updateQuestion(@PathVariable int questionId, @RequestBody Question updatedQuestion) {
	       
	        Question existingQuestion = questionRepository.findByQtnId(questionId);
	        if (existingQuestion == null) {
	            throw new RuntimeException("No question found with ID: " + questionId);
	        }

	       
	        existingQuestion.setText(updatedQuestion.getText()); 
	        existingQuestion.setOptions(updatedQuestion.getOptions()); 
	        existingQuestion.setCorrect(updatedQuestion.getCorrect()); 

	        
	        Question savedQuestion = questionRepository.save(existingQuestion);

	        
	        return ResponseEntity.ok(savedQuestion);
	    }
	    
	    @DeleteMapping("/delete-question/{questionId}")
	    public ResponseEntity<String> deleteQuestion(@PathVariable int questionId) {
	        
	        Question question = questionRepository.findByQtnId(questionId);
	        if (question == null) {
	            throw new RuntimeException("No question found with ID: " + questionId);
	        }

	  
	        questionRepository.delete(question);
	        return ResponseEntity.ok("Question deleted successfully.");
	    }


	    @GetMapping("/user/{id}")
	    public UserSession getSessionStats(@PathVariable int id) {
	        return userSessionRepository.findById(id);
	    }
}
