package com.alura.forohub.topic;

import java.time.LocalDateTime;

public record TopicListData(
    Long id,
    String title,
    String message,
    String course,
    LocalDateTime modifiedDate,
    String author,
    Boolean status) {
  /**
   * Constructs a new TopicListData from a Topic entity.
   *
   * @param topic the Topic entity
   */
  public TopicListData(Topic topic) {
    this(
        topic.getId(),
        topic.getTitle(),
        topic.getMessage(),
        topic.getCourse().getName(),
        topic.getModifiedDate(),
        topic.getAuthor(),
        topic.getStatus());
  }
}
