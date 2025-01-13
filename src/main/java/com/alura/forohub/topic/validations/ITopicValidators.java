package com.alura.forohub.topic.validations;

import com.alura.forohub.topic.TopicListData;

/** General interface for the rest of the validators. */
public interface ITopicValidators {
  /**
   * Receives the data to validate.
   *
   * @param data Data requests from controllers.
   */
  void validate(TopicListData data);
}
