package com.app.service;

import com.app.dto.Student;

public interface IStudentService {

	String save(Student student); //Creating(Inserting) a record
	Student findByID(Integer sid); //Reading a record
	String updateByID(Student student); //Updating a record
	String deleteByID(Integer sid); //deleting a record
}
