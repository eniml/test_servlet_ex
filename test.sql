DROP TABLE IF EXISTS groups;
DROP TABLE IF EXISTS students;

CREATE TABLE groups (
  id INT NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE students (
  id         INT NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(15),
  last_name  VARCHAR(20),
  group_id   INT,
  PRIMARY KEY (id),
  FOREIGN KEY (group_id) REFERENCES groups (id)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);

INSERT INTO groups (id)
VALUES (123),
  (124),
  (456),
  (789);

INSERT INTO students (first_name, last_name, group_id)
VALUES ('ivan', 'ivanov', 123),
  ('petor', 'sidorov', 124),
  ('anna', 'karenina', 456),
  ('kon', 'pedalnij', 789);


