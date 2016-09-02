use mydatabase;


INSERT INTO TABLE student_count_report
SELECT
  school.school_name,
  count(student.student_id) as cnt
FROM school
LEFT JOIN student on student.school_id = school.school_id
GROUP BY school.school_name;