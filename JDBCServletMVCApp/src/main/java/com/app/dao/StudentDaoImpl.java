package com.app.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.app.dto.Student;
import com.app.util.JdbcUtil;



public class StudentDaoImpl implements IStudentDao {
	Connection connection = null;
	

	@Override
	public String save(Student student) {
		String sqlInsertQuery = "insert into student(`sname`,`sage`,`saddr`) values(?,?,?)";
		PreparedStatement pstmt =null;
		String status =null;
		try {
			connection= JdbcUtil.getConnection();
			
			if (connection!=null) 
				pstmt=connection.prepareStatement(sqlInsertQuery );
			if(pstmt !=null) {
				pstmt.setString(1, student.getSname());
				pstmt.setInt(2, student.getSage());
				pstmt.setString(3, student.getSaddr());
				
				int rowsAffected=pstmt.executeUpdate();
				if (rowsAffected==1) {
					status="success";
				}else {
					status="failure";
				}
			}
			
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public Student findByID(Integer sid) {
		PreparedStatement pstmt = null;
		String sqlSelectQuery = "select sid,sname,sage,saddr from student where sid=?";
		Student student = null;
		try {
			connection = JdbcUtil.getConnection();
			
			if (connection!=null)
				pstmt = connection.prepareStatement(sqlSelectQuery );
			if (pstmt!=null)
				pstmt.setInt(1, sid);
			if (pstmt!=null) {
				ResultSet resultSet = pstmt.executeQuery();
				if(resultSet.next()) {
					student = new Student();
					student.setSid(resultSet.getInt(1));
					student.setSname(resultSet.getString(2));
					student.setSage(resultSet.getInt(3));
					student.setSaddr(resultSet.getString(4));
				}
			}
				
			
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return student;
	}

	@Override
	public String updateByID(Student student) {
		PreparedStatement pstmt = null;
		String sqlUpdateQuery = "update student set sname=?,sage=?,saddr=? where sid = ?";
		String status = null;
		try {
			connection = JdbcUtil.getConnection();
			
			if (connection!=null)
				pstmt = connection.prepareStatement(sqlUpdateQuery );
			if (pstmt!=null) {
				pstmt.setString(1, student.getSname());
				pstmt.setInt(2, student.getSage());
				pstmt.setString(3, student.getSaddr());
				pstmt.setInt(4, student.getSid());
			}
			if(pstmt!=null) {
				int rowsAffected = pstmt.executeUpdate();
				if(rowsAffected==1) {
					status = "success";
				}else
					status = "failure";
			}
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public String deleteByID(Integer sid) {
		PreparedStatement pstmt = null;
		String sqlDeleteQuery = "delete from student where sid=?";
		String status =null;
		try {
			connection = JdbcUtil.getConnection();
			
			if (connection!=null)
				pstmt = connection.prepareStatement(sqlDeleteQuery );
			if (pstmt!=null) {
				pstmt.setInt(1, sid);
				int rowsAffected=pstmt.executeUpdate();
				if (rowsAffected==1) {
					status="success";
				}else {
					status="failure";
				}
			}
				
				
			
				
			
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}

}
