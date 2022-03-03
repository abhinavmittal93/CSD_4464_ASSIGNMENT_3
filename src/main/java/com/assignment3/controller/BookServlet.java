package com.assignment3.controller;
/**
 * @author Abhinav, Aarti, Samridhi
 * Date - 02 Feb 2022
 * Description - This servlet handles the request related to add, update, issue and delete books.
 * 
 */

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.assignment3.dao.BookDaoClass_H;
import com.assignment3.model.BookClass_H;
import com.assignment3.model.MemberClass_H;
import com.assignment3.model.PublisherClass_H;

/**
 * Servlet implementation class BookServlet
 * 
 */
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BookDaoClass_H bookDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		bookDao = new BookDaoClass_H();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("Book.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message = "Book is added successfully.";
		String action = request.getParameter("action");
		int result = 0;
		Long bookId = 0L;
		
		
		if ("UPDATE".equalsIgnoreCase(action) || "ISSUE".equalsIgnoreCase(action) || "DELETE".equalsIgnoreCase(action)) {
			if (request.getParameter("bookId").isBlank()) {
				request.setAttribute("message", "Please enter/select the Book ID to " + action +" !!!");
				RequestDispatcher dispatcher = request.getRequestDispatcher("Response.jsp");
				dispatcher.forward(request, response);
			}
			
			bookId = Long.valueOf(request.getParameter("bookId"));
		}
		
		BookClass_H bookClass_H = null;
		if("UPDATE".equalsIgnoreCase(action) || "ADD".equalsIgnoreCase(action)) {
			String title = request.getParameter("title");
			String author = request.getParameter("author");
			String price = request.getParameter("price");
			String available = request.getParameter("available");
			String pubId = request.getParameter("pubId");
			

			PublisherClass_H publisherClass_H = new PublisherClass_H();
			publisherClass_H.setPubId(Long.valueOf(pubId));
			

			bookClass_H = new BookClass_H();
			bookClass_H.setBookId(bookId);
			bookClass_H.setTitle(title);
			bookClass_H.setAuthor(author);
			bookClass_H.setAvailable(available.charAt(0));
			bookClass_H.setPrice(Double.parseDouble(price));
			bookClass_H.setPublisherClass_H(publisherClass_H);
		}

		try {
			if ("UPDATE".equalsIgnoreCase(action)) {
				result = bookDao.updateBook(bookClass_H);
				message = "Book is updated successfully.";
			} else if("ISSUE".equalsIgnoreCase(action)) {
				bookClass_H = new BookClass_H();
				bookClass_H.setBookId(bookId);
				String membId = request.getParameter("membId");
				MemberClass_H memberClass_H = new MemberClass_H();
				memberClass_H.setMembId(Long.valueOf(membId));
				bookClass_H.setMemberClass_H(memberClass_H);
				
				java.util.Date date = new java.util.Date();
				Date issueDate = new Date(date.getTime());
				
				Calendar now = Calendar.getInstance(); // creates the Calendar object of the current time
				now.add(Calendar.MONTH, 1); // add 1 month to current date
				Date dueDate = new Date((now.getTime()).getTime());// creates the sql Date of the above created object
				
				bookClass_H.setIssueDate(issueDate);
				bookClass_H.setDueDate(dueDate);
				
				result = bookDao.issueBook(bookClass_H);
				message = "Book is issued successfully.";
			} else if("DELETE".equalsIgnoreCase(action)) {
				result = bookDao.deleteBook(bookId);
				message = "Book is deleted successfully.";
			} else {
				result = bookDao.addBook(bookClass_H);
			} 
			
			if (result == 0) {
				message = "Error occurred. Please try again.";
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "Error occurred. Please try again.";
		}

		request.setAttribute("message", message);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Response.jsp");
		dispatcher.forward(request, response);
	}

}
