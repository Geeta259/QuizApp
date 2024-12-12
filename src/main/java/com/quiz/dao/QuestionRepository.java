package com.quiz.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.quiz.entities.Question;


public interface QuestionRepository extends JpaRepository<Question, Integer>{

	@Query("SELECT q FROM Question q WHERE q.qtnid = :qtnid")
    Question findByQtnId(@Param("qtnid") int qtnid);
}
