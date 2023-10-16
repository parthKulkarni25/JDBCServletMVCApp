package com.app.factory;

import com.app.dao.IStudentDao;
import com.app.dao.StudentDaoImpl;

public class StudentDaoFactory {
	
	private static IStudentDao studentDao = null;
	
	private StudentDaoFactory() {
		// TODO Auto-generated constructor stub
	}
	
	public static IStudentDao getStudentDao() {
		
		if(studentDao ==null)
			studentDao = new StudentDaoImpl();
		return studentDao;
	}

}
