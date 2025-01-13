CREATE TABLE courses (
  id BIGINT NOT NULL AUTO_INCREMENT,
  course_name VARCHAR(255) NOT NULL,
  category VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO
  courses (course_name, category)
VALUES
  (
    'Spring Boot 3: desarrollar una API Rest en Java',
    'JAVA'
  );

INSERT INTO
  courses (course_name, category)
VALUES
  (
    'Spring Boot 3: aplique las mejores prácticas y proteja una API Rest',
    'JAVA'
  );

INSERT INTO
  courses (course_name, category)
VALUES
  (
    'Spring Boot 3: documentar,
probar y preparar una API para su implementación',
    'JAVA'
  );