package com.jdbc.dao;

import java.util.List;

import com.jdbc.student.Student;

public interface StudentDao {
	void insert(Student s);
	List<Student> getAll();
	void update(String  s, int id);
	void delete(int id);
	void updatePrepareStatement(String s, int id);
	List<Student> getAllByResultsetExtractor();
	Student findById(int id);
	List<Student> getAllRowMap();
}
