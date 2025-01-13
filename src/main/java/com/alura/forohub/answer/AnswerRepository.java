package com.alura.forohub.answer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
  @Query(
      """
      SELECT a
      FROM Answer a
      WHERE a.topic.id = :id
      """)
  Page<Answer> findAnswersByTopicId(Long id, Pageable pagination);
}
