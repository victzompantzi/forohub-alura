CREATE TABLE topics (
  id BIGINT NOT NULL AUTO_INCREMENT,
  title VARCHAR(255) NOT NULL UNIQUE,
  message VARCHAR(255) NOT NULL UNIQUE,
  creation_date DATETIME NOT NULL,
  author VARCHAR(255) NOT NULL,
  STATUS TINYINT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (id)
);