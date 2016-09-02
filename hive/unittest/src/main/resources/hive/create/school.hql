use mydatabase;


CREATE TABLE IF NOT EXISTS school
(
  school_id   BIGINT,
  school_name        string

) ROW FORMAT delimited fields terminated BY ',';
