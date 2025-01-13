package com.alura.forohub.topic;

import com.alura.forohub.course.Course;
import com.alura.forohub.course.CourseRepository;
import com.alura.forohub.user.User;
import com.alura.forohub.user.UserRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody; // Add this import
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topic")
@SecurityRequirement(name = "bearer-key")
public class TopicController {

  @Autowired private TopicRepository topicRepository;
  @Autowired private UserRepository userRepository;
  @Autowired private CourseRepository courseRepository;

  // @Autowired List<ITopicValidators> validators;

  @PostMapping
  @Transactional
  public ResponseEntity<TopicListData> createTopic(
      @RequestBody @Valid TopicCreateData topicData, // Changed to Spring's @RequestBody
      UriComponentsBuilder uriComponentsBuilder,
      @AuthenticationPrincipal UserDetails userDetails) {

    User author = (User) userRepository.findByUsername(userDetails.getUsername());
    Course course =
        courseRepository
            .findById(topicData.courseId())
            .orElseThrow(() -> new IllegalArgumentException("Invalid course ID"));
    Topic newTopic = topicRepository.save(new Topic(topicData, author, course));
    // Build the list response
    TopicListData topicListData =
        new TopicListData(
            newTopic.getId(),
            newTopic.getTitle(),
            newTopic.getMessage(),
            newTopic.getCourse().getName(),
            newTopic.getModifiedDate(),
            newTopic.getAuthor(),
            newTopic.getStatus());

    URI url = uriComponentsBuilder.path("/topic/{id}").buildAndExpand(newTopic.getId()).toUri();
    return ResponseEntity.created(url).body(topicListData);
  }

  // Get all the topics.
  @GetMapping
  public ResponseEntity<Page<TopicListData>> listTopics(
      @PageableDefault(size = 10, sort = "modifiedDate", direction = Sort.Direction.ASC)
          Pageable pagination) {
    Page<Topic> topics = topicRepository.findAll(pagination);
    return ResponseEntity.ok(topics.map(TopicListData::new));
  }

  // Get all topics by status and current user.
  // @GetMapping
  // public ResponseEntity<Page<TopicListData>> listTopics(
  //     @PageableDefault(size = 10) Pageable pagination,
  //     @AuthenticationPrincipal UserDetails userDetails) {
  //   return ResponseEntity.ok(
  //       topicRepository
  //           .findByStatusTrueAndAuthor(userDetails.getUsername(), pagination)
  //           .map(TopicListData::new));
  // }

  /**
   * Get a Topic by id.
   *
   * @param id the id of the topic
   * @return the default data provided by the 'TopicListData' DTO
   */
  @GetMapping("/{id}")
  public ResponseEntity<TopicListData> getTopicById(@PathVariable Long id) {
    Optional<Topic> topic = topicRepository.findById(id);
    if (topic.isPresent()) {
      Topic activeTopic = topic.get();
      TopicListData dataTopic =
          new TopicListData(
              activeTopic.getId(),
              activeTopic.getTitle(),
              activeTopic.getMessage(),
              activeTopic.getCourse().getName(),
              activeTopic.getModifiedDate(),
              activeTopic.getAuthor(),
              activeTopic.getStatus());
      return ResponseEntity.ok(dataTopic);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PutMapping("/{id}")
  @Transactional
  public ResponseEntity<TopicListData> updateTopic(
      @PathVariable Long id, @RequestBody @Valid TopicUpdateData topicUpdateData) {
    Optional<Topic> topic = topicRepository.findById(id);
    if (topic.isPresent()) {
      Topic topicToUpdate = topic.get();
      topicToUpdate.updateTopic(topicUpdateData);
      return ResponseEntity.ok(
          new TopicListData(
              topicToUpdate.getId(),
              topicToUpdate.getTitle(),
              topicToUpdate.getMessage(),
              topicToUpdate.getCourse().getName(),
              topicToUpdate.getModifiedDate(),
              topicToUpdate.getAuthor(),
              topicToUpdate.getStatus()));
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  @Transactional
  public ResponseEntity<TopicListData> deleteTopic(@PathVariable Long id) {
    Optional<Topic> topic = topicRepository.findById(id);
    if (topic.isPresent()) {
      topicRepository.deleteById(id);
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
