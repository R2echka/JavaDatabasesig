-- liquibase formatted sql

-- changeset r2echka:1
CREATE INDEX student_name_index ON student (name);
CREATE INDEX faculty_cn_index ON faculty (color, name);