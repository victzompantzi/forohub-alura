package com.alura.forohub.answer;

import com.alura.forohub.topic.Topic;
import com.alura.forohub.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "answers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Answer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;
  private String author;
  private String message;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "topic_id")
  private Topic topic;

  @Column(name = "creation_date")
  private LocalDateTime modifiedDateTime;

  public Answer(AnswerCreateData answerCreateData, User author, Topic topic) {
    this.title = answerCreateData.title();
    this.author = author.getUsername();
    this.message = answerCreateData.message();
    this.topic = topic;
    this.modifiedDateTime = LocalDateTime.now();
  }
}
