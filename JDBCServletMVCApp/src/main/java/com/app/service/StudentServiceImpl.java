package com.app.service;

import com.app.dao.IStudentDao;
import com.app.dto.Student;
import com.app.factory.StudentDaoFactory;

public class StudentServiceImpl implements IStudentService {
	
	IStudentDao dao;

	@Override
	public String save(Student student) {
		dao = StudentDaoFactory.getStudentDao();
		
		
		return dao.save(student);
	}

	@Override
	public Student findByID(Integer sid) {
		dao = StudentDaoFactory.getStudentDao();
		return dao.findByID(sid);
	}

	@Override
	public String updateByID(Student student) {
		dao = StudentDaoFactory.getStudentDao();
		return dao.updateByID(student);
	}

	@Override
	public String deleteByID(Integer sid) {
		dao = StudentDaoFactory.getStudentDao();
		return dao.deleteByID(sid);
	}

}
