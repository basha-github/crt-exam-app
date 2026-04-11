package com.crt.exam.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crt.exam.entity.Question;

public interface QuestionsRepo  extends JpaRepository<Question, String> {

}
