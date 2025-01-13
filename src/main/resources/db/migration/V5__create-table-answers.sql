CREATE TABLE answers (
  id BIGINT NOT NULL AUTO_INCREMENT,
  creation_date DATETIME NOT NULL,
  title VARCHAR(255) NOT NULL,
  message TEXT NOT NULL,
  author VARCHAR(255) NOT NULL,
  topic_id BIGINT NOT NULL,
  PRIMARY KEY (id)
);