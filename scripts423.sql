SELECT student.name, student.age, faculty.name from student
join faculty on student.faculty_id = faculty.id;

SELECT student.* from student
join avatar on avatar.student_id = student.id;