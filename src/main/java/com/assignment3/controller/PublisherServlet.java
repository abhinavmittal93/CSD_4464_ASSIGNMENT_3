package com.assignment3.controller;

/**
 * @author Navdeep, Syed Ahmed
 * Date - 02 Feb 2022
 * Description - This servlet handles the request related to register and update of publishers.
 * 
 */

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.assignment3.dao.PublisherDaoClass_H;
import com.assignment3.model.PublisherClass_H;

/**
 * Servlet implementation class PublisherServlet
 */
public class PublisherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private PublisherDaoClass_H publisherDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublisherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		publisherDao = new PublisherDaoClass_H();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("Publisher.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message = "Publisher is added successfully.";
		String action = request.getParameter("action");
		int result = 0;
		Long pubId = 0L;
		if ("UPDATE".equalsIgnoreCase(action)) {
			if (request.getParameter("pubId").isBlank()) {
				request.setAttribute("message", "Please enter the Publisher ID to update!!!");
				RequestDispatcher dispatcher = request.getRequestDispatcher("Response.jsp");
				dispatcher.forward(request, response);
			}
			pubId = Long.valueOf(request.getParameter("pubId"));
			message = "Publisher is updated successfully.";
		}

		String name = request.getParameter("name");
		String address = request.getParameter("address");

		PublisherClass_H publisherClass_H = new PublisherClass_H();
		publisherClass_H.setPubId(pubId);
		publisherClass_H.setName(name);
		publisherClass_H.setAddress(address);
		try {
			if ("UPDATE".equalsIgnoreCase(action)) {
				result = publisherDao.updatePublisher(publisherClass_H);
			} else {
				result = publisherDao.registerPublisher(publisherClass_H);
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
