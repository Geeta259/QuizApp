package com.quiz.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserSession {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int total;
    private int correct;
    private int incorrect;
	public UserSession() {
		super();
	}
	public UserSession(int id, int total, int correct, int incorrect) {
		super();
		this.id = id;
		this.total = total;
		this.correct = correct;
		this.incorrect = incorrect;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getCorrect() {
		return correct;
	}
	public void setCorrect(int correct) {
		this.correct = correct;
	}
	public int getIncorrect() {
		return incorrect;
	}
	public void setIncorrect(int incorrect) {
		this.incorrect = incorrect;
	}
	@Override
	public String toString() {
		return "UserSession [id=" + id + ", total=" + total + ", correct=" + correct + ", incorrect=" + incorrect + "]";
	}
    
	
    
}
