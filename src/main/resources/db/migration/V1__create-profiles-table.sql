CREATE TABLE profiles (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO profiles (name) VALUES ('ADMINISTRATOR');
INSERT INTO profiles (name) VALUES ('MODERATOR');
INSERT INTO profiles (name) VALUES ('USER');
