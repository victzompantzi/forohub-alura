package com.alura.forohub.answer;

import java.time.LocalDateTime;

public record AnswerListData(
    Long id,
    String title,
    String message,
    LocalDateTime modifiedDateTime,
    String author,
    Long topicId) {
  public AnswerListData(Answer answer) {
    this(
        answer.getId(),
        answer.getTitle(),
        answer.getMessage(),
        answer.getModifiedDateTime(),
        answer.getAuthor(),
        answer.getTopic().getId());
  }
}
