package com.assignment3.controller;

/**
 * @author Aarti, Samridhi
 * Date - 02 Feb 2022
 * Description - This servlet handles the request related to register and update of members.
 * 
 */

import java.io.IOException;
import java.util.Calendar;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.assignment3.dao.MemberDaoClass_H;
import com.assignment3.model.MemberClass_H;

/**
 * Servlet implementation class MemberServlet
 */
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MemberDaoClass_H memberDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		memberDao = new MemberDaoClass_H();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("Member.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String message = "Member is added successfully.";
		String action = request.getParameter("action");
		int result = 0;
		Long membId = 0L;
		if ("UPDATE".equalsIgnoreCase(action)) {
			if (request.getParameter("membId").isBlank()) {
				request.setAttribute("message", "Please enter the Member ID to update!!!");
				RequestDispatcher dispatcher = request.getRequestDispatcher("Response.jsp");
				dispatcher.forward(request, response);
			}
			membId = Long.valueOf(request.getParameter("membId"));
			message = "Member is updated successfully.";
		}

		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String membType = request.getParameter("membType");

		MemberClass_H member = new MemberClass_H();
		member.setMembId(membId);
		member.setAddress(address);
		member.setMembType(membType);
		member.setName(name);

		java.util.Date date = new java.util.Date();
		member.setMembDate(new Date(date.getTime()));

		Calendar now = Calendar.getInstance(); // creates the Calendar object of the current time
		now.add(Calendar.MONTH, 6); // add 6 months to current date
		member.setExpiryDate(new Date((now.getTime()).getTime())); // creates the sql Date of the above created object

		try {
			if ("UPDATE".equalsIgnoreCase(action)) {
				result = memberDao.updateMember(member);
			} else {
				result = memberDao.registerMember(member);
			}
			
			if(result == 0) {
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
