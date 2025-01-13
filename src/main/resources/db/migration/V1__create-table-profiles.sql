CREATE TABLE profiles (
  id BIGINT NOT NULL AUTO_INCREMENT,
  profile_name VARCHAR(50) NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO
  profiles (profile_name)
VALUES
  ('ADMINISTRATOR');

INSERT INTO
  profiles (profile_name)
VALUES
  ('MODERATOR');

INSERT INTO
  profiles (profile_name)
VALUES
  ('USER');