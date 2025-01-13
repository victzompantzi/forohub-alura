ALTER TABLE
  topics
ADD
  course_id BIGINT NOT NULL DEFAULT 1;

ALTER TABLE
  topics
ADD
  FOREIGN KEY (course_id) REFERENCES courses (id);