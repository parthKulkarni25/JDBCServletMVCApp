package com.app.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.dto.Student;
import com.app.factory.StudentServiceFactory;
import com.app.service.IStudentService;


@WebServlet("/controller/*")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI =request.getRequestURI();
		System.out.println(requestURI);
		RequestDispatcher rd  = null;
		
		IStudentService service = StudentServiceFactory.getStudentService();
		if(requestURI.endsWith("layout")) {
			rd = request.getRequestDispatcher("../layout.html");
			rd.forward(request, response);
		}
		if(requestURI.endsWith("addform")) {
			String sname = request.getParameter("sname");
			String sage = request.getParameter("sage");
			String saddr = request.getParameter("saddr");
			
			Student student = new Student();
			student.setSname(sname);
			student.setSage(Integer.parseInt(sage));
			student.setSaddr(saddr);
			
			String status = service.save(student);
			System.out.println(status);
			
			if(status.equals("success")) {
				rd = request.getRequestDispatcher("../success.html");
				rd.forward(request, response);
			}else {
				rd = request.getRequestDispatcher("../failure.html");
				rd.forward(request, response);
			}
		}
		if(requestURI.endsWith("searchform")) {
			String sid = request.getParameter("sid");
			Student student =service.findByID(Integer.parseInt(sid));
			
			if(student!=null) {
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				
				out.println("<html><head><title>Output</title></head>");
				out.println("<body bgcolor=\"cyan\" text=\"red\">");
				out.println("<h1 align = 'center'>Student records</h1>");
				out.println("<table border='1' align='center'>");
				out.println("<tr>");
	            out.println("<tr><th>SID</th><td>"+student.getSid()+"</td>");
	            out.println("<tr><th>SNAME</th><td>"+student.getSname()+"</td>");
	            out.println("<tr><th>SAGE</th><td>"+student.getSage()+"</td>");
	            out.println("<tr><th>SADDR</th><td>"+student.getSaddr()+"</td>");
	            out.println("</tr>");
	            out.println("</table>");
	            out.println("</body>");
	            out.println("</html>");
	   	
	            
			}else {
				rd = request.getRequestDispatcher("../failure.html");
				rd.forward(request, response);
			}
		}
		if(requestURI.endsWith("deleteform")) {
			String sid = request.getParameter("sid");
			String status =service.deleteByID(Integer.parseInt(sid));
			
			if(status.equals("success")) {
				rd = request.getRequestDispatcher("../success.html");
				rd.forward(request, response);
			}else {
				rd = request.getRequestDispatcher("../failure.html");
				rd.forward(request, response);
			}
		}
		if(requestURI.endsWith("editform")) {
			String sid = request.getParameter("sid");
			Student student =service.findByID(Integer.parseInt(sid));
			if (student!=null) {
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.println("<html><head><title>OUTPUT</title></head>");
				out.println("<body bgcolor='lightblue'>");
				out.println("<br/><br/><br/>");
				out.println("<form method='post' action='./update'>");
				out.println("<table align='center'>");
				out.println("<tr><th>ID</th><td>" + student.getSid() + "</td></tr>");
				out.println("<input type='hidden' name='sid' value='" + student.getSid() + "'/>");
				out.println("<tr><th>NAME</th><td><input type='text' name='sname' value='" + student.getSname()
						+ "'/></td></tr>");
				out.println("<tr><th>AGE</th><td><input type='text' name='sage' value='" + student.getSage()
						+ "'/></td></tr>");
				out.println("<tr><th>ADDRESS</th><td><input type='text' name='saddr' value='" + student.getSaddr()
						+ "'/></td></tr>");
				out.println("<tr><td></td><td><input type='submit' value='update'/></td></tr>");
				out.println("</table>");
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
				out.close();
				
			} else {
				rd = request.getRequestDispatcher("../failure.html");
				rd.forward(request, response);
			}
			
		}
		if(requestURI.endsWith("update")) {
			String sid = request.getParameter("sid");
			String sname = request.getParameter("sname");
			String sage = request.getParameter("sage");
			String saddr = request.getParameter("saddr");
			
			Student student = new Student();
			student.setSid(Integer.parseInt(sid));
			student.setSname(sname);
			student.setSage(Integer.parseInt(sage));
			student.setSaddr(saddr);
			
			String status=service.updateByID(student);
			if(status.equals("success")) {
				rd = request.getRequestDispatcher("../success.html");
				rd.forward(request, response);
			}else {
				rd = request.getRequestDispatcher("../failure.html");
				rd.forward(request, response);
			}
		}
		
	}

}
