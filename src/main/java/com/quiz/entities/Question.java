package com.quiz.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Question {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int qtnid;
    private String text;
    private String correct;
    
    private List<String> options;
    
    
	public Question() {
		super();	
	}

	public Question(int qtnid, String text, String correct, List<String> options) {
		super();
		this.qtnid = qtnid;
		this.text = text;
		this.correct = correct;
		this.options = options;
	}

	

	public int getQtnid() {
		return qtnid;
	}

	public void setQtnid(int qtnid) {
		this.qtnid = qtnid;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getCorrect() {
		return correct;
	}

	public void setCorrect(String correct) {
		this.correct = correct;
	}

	public List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}

	@Override
	public String toString() {
		return "Question [qtnid=" + qtnid + ", text=" + text + ", correct=" + correct + ", options=" + options + "]";
	}
    
	
    
}
