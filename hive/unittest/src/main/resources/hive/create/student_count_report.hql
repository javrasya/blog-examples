use mydatabase;


CREATE TABLE IF NOT EXISTS student_count_report
(
  school_name  string,
  student_coun BIGINT

) ROW FORMAT delimited fields terminated BY ',';
