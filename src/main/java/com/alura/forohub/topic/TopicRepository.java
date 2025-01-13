package com.alura.forohub.topic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TopicRepository extends JpaRepository<Topic, Long> {

  Page<Topic> findByStatusTrueAndAuthor(String author, Pageable pagination);

  @Query(
      """
      SELECT t.status
      FROM Topic t
      WHERE t.id = :id
      """)
  Boolean findActiveTopicById(Long id);
}
