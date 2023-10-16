package com.app.factory;

import com.app.service.IStudentService;
import com.app.service.StudentServiceImpl;

public class StudentServiceFactory {
	
	private static IStudentService studentService = null;
	
	private StudentServiceFactory() {
		
	}
	
	public static IStudentService getStudentService() {
		
		if(studentService == null)
			studentService = new StudentServiceImpl();
		
		return studentService;
	}

}
