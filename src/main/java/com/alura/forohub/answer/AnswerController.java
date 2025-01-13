package com.alura.forohub.answer;

import com.alura.forohub.exception.CustomExceptionValidator;
import com.alura.forohub.topic.Topic;
import com.alura.forohub.topic.TopicRepository;
import com.alura.forohub.user.User;
import com.alura.forohub.user.UserRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topic/{topicId}/answers")
@SecurityRequirement(name = "bearer-key")
public class AnswerController {

  @Autowired private AnswerRepository answerRepository;

  @Autowired private UserRepository userRepository;

  @Autowired private TopicRepository topicRepository;

  @PostMapping
  @Transactional
  public ResponseEntity<AnswerListData> addAnswer(
      @PathVariable Long topicId,
      @RequestBody @Valid AnswerCreateData answerCreateData,
      UriComponentsBuilder uriComponentsBuilder,
      @AuthenticationPrincipal UserDetails userDetails) {
    User author = (User) userRepository.findByUsername(userDetails.getUsername());
    Topic topic =
        topicRepository
            .findById(topicId)
            .orElseThrow(() -> new CustomExceptionValidator("Topic not found"));
    Answer newAnswer = answerRepository.save(new Answer(answerCreateData, author, topic));
    // Build the list of the topic and its answers.
    AnswerListData answerListData =
        new AnswerListData(
            newAnswer.getId(),
            newAnswer.getTitle(),
            newAnswer.getMessage(),
            newAnswer.getModifiedDateTime(),
            newAnswer.getAuthor(),
            newAnswer.getTopic().getId());
    URI url = uriComponentsBuilder.path("/topic/{topicId}").buildAndExpand(topic.getId()).toUri();
    return ResponseEntity.created(url).body(answerListData);
  }

  @GetMapping
  public ResponseEntity<Page<AnswerListData>> getAnswersByTopic(
      @PathVariable Long topicId, @PageableDefault(size = 10) Pageable pagination) {
    return ResponseEntity.ok(
        answerRepository.findAnswersByTopicId(topicId, pagination).map(AnswerListData::new));
  }
}
