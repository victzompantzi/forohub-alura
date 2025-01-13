CREATE TABLE users (
  id BIGINT NOT NULL AUTO_INCREMENT,
  username VARCHAR(255) NOT NULL,
  user_password VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL UNIQUE,
  status_account BOOLEAN NOT NULL DEFAULT TRUE,
  profile_id BIGINT NOT NULL DEFAULT 3,
  PRIMARY KEY (id),
  FOREIGN KEY (profile_id) REFERENCES profiles (id)
);

INSERT INTO
  users (username, user_password, email)
VALUES
  (
    'vic.hugo',
    '$2a$10$xVL/f8KEDXCdIPzwR6vHgOtGS1QbYnjcw2zutcP729K0Goq9rTE5O',
    'vic.hugo@gmail.com'
  );