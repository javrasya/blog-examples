use mydatabase;


CREATE TABLE IF NOT EXISTS student
(
  student_id BIGINT,
  name       string,
  lastname   string,
  birth_date string,
  school_id  BIGINT

) ROW FORMAT delimited fields terminated BY ',';
