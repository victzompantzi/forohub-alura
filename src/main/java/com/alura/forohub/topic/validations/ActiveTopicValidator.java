package com.alura.forohub.topic.validations;

import com.alura.forohub.exception.CustomExceptionValidator;
import com.alura.forohub.topic.TopicListData;
import com.alura.forohub.topic.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActiveTopicValidator implements ITopicValidators {

  @Autowired TopicRepository topicRepository;

  public void validate(TopicListData topicData) {
    if (topicData.id() == null) {
      return;
    }
    var activeTopic = topicRepository.findActiveTopicById(topicData.id());
    if (!activeTopic) {
      throw new CustomExceptionValidator("Tópico inactivo");
    }
  }
}
