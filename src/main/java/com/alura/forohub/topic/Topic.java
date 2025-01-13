package com.alura.forohub.topic;

import com.alura.forohub.answer.Answer;
import com.alura.forohub.course.Course;
import com.alura.forohub.user.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "topics")
@Entity(name = "Topic")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;
  private String message;

  @Column(name = "creation_date")
  private LocalDateTime modifiedDate;

  private String author;
  private Boolean status;

  @OneToMany(
      mappedBy = "topic",
      fetch = FetchType.LAZY,
      cascade = CascadeType.ALL,
      orphanRemoval = true)
  private List<Answer> answers = new ArrayList<>();

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "course_id")
  private Course course;

  // Remove the CourseRepository dependency
  // @Autowired private CourseRepository courseRepository;

  // Constructor of a new topic
  public Topic(TopicCreateData topicData, User author, Course course) {
    this.title = topicData.title();
    this.message = topicData.message();
    this.modifiedDate = LocalDateTime.now();
    this.course = course;
    this.status = true;
    this.author = author.getUsername();
  }

  // Method to update a topic
  public void updateTopic(TopicUpdateData topicUpdateData) {
    int flag = 0;
    if (topicUpdateData.title() != null) {
      this.title = topicUpdateData.title();
      flag++;
    }
    if (topicUpdateData.message() != null) {
      this.message = topicUpdateData.message();
      flag++;
    }
    if (flag != 0) {
      this.modifiedDate = LocalDateTime.now();
    }
  }

  public void disableTopic() {
    this.status = false;
  }

  // public void addAnswer(Answer answer) {
  //   answers.add(answer);
  //   answer.setTopic(this);
  // }

  // public void removeAnswer(Answer answer) {
  //   answers.remove(answer);
  //   answer.setTopic(null);
  // }
}
