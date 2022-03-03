package com.assignment3.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.assignment3.model.PublisherClass_H;

/**
 * @author Abhinav, Navdeep, Syed Ahmed
 * Date - 02 Feb 2022
 * Description - This is a DAO class which handles the Database queries related to publisher table 
 * and transform the retrieved data and return to PublisherServlet.
 */
public class PublisherDaoClass_H {

	/**
	 * It adds a new record in publisher table with the details provided by the user.
	 * 
	 * @param publisherClass_H
	 * @return
	 * @throws ClassNotFoundException
	 */
	public int registerPublisher(PublisherClass_H publisherClass_H) throws ClassNotFoundException {
		// create sql statement
		String INSERT_PUBLISHER_SQL = "INSERT INTO publisher (name, address) VALUES (?,?);";

		int result = 0;

		Class.forName("com.mysql.cj.jdbc.Driver");

		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment3", "root",
				"root"); PreparedStatement ps = connection.prepareStatement(INSERT_PUBLISHER_SQL)) {

			ps.setString(1, publisherClass_H.getName());
			ps.setString(2, publisherClass_H.getAddress());

			System.out.println(ps);

			result = ps.executeUpdate();
			ps.close();
		}

		catch (SQLException e) {
			printSQLException(e); // calling printSQLException function...
		}
		return result;

	}

	/*
	 * Exception -function for printing SQL State, Error Code and Message ..
	 */
	private void printSQLException(SQLException ex) {

		for (Throwable e : ex) {
			if (e instanceof SQLException) {

				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + ((SQLException) e).getMessage());

				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause" + t);
					t = t.getCause();
				}
			}

		}

	}

	/**
	 * It updates the publisher details by pubId with the provided details.
	 * 
	 * @param publisherClass_H
	 * @return
	 * @throws ClassNotFoundException
	 */
	public int updatePublisher(PublisherClass_H publisherClass_H) throws ClassNotFoundException {
		// create sql statement
		String UPDATE_PUBLISHER_SQL = "UPDATE publisher SET name = ?, address = ? WHERE pubId = ?";

		int result = 0;

		Class.forName("com.mysql.cj.jdbc.Driver");

		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment3", "root",
				"root"); PreparedStatement ps = connection.prepareStatement(UPDATE_PUBLISHER_SQL)) {

			ps.setString(1, publisherClass_H.getName());
			ps.setString(2, publisherClass_H.getAddress());
			ps.setLong(3, publisherClass_H.getPubId());

			System.out.println(ps);

			result = ps.executeUpdate();
			ps.close();
		}

		catch (SQLException e) {
			printSQLException(e); // calling printSQLException function...
		}
		return result;

	}
	
	/**
	 * 
	 * It retrieves the publisher details by pubId, from publisher table and set it in the Model and return it.
	 * 
	 * @param pubId
	 * @return
	 * @throws ClassNotFoundException
	 */
	public PublisherClass_H getPublisherDetails(long pubId) throws ClassNotFoundException {
		
		PublisherClass_H publisherClass_H = new PublisherClass_H();
		
		// create sql statement
		String SELECT_PUBLISHER_SQL = "SELECT * FROM publisher WHERE pubId = " + pubId;

		Class.forName("com.mysql.cj.jdbc.Driver");

		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment3", "root",
				"root"); Statement selectStmt = connection.createStatement();) {
			
			ResultSet rs = selectStmt.executeQuery(SELECT_PUBLISHER_SQL);
			
			while (rs.next()) {
				publisherClass_H.setPubId(rs.getLong("pubId"));
				publisherClass_H.setName(rs.getString("name"));
				publisherClass_H.setAddress(rs.getString("address"));
			}
			selectStmt.close();
		}

		catch (SQLException e) {
			printSQLException(e); // calling printSQLException function...
		}

		return publisherClass_H;
	}
	
	/**
	 * 
	 * It retrieves all the records from publisher table and set it in the Model and return the List.
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 */
	public List<PublisherClass_H> getAllPublishers() throws ClassNotFoundException {
		List<PublisherClass_H> publisherList = new ArrayList<PublisherClass_H>();
		
		// create sql statement
		String SELECT_PUBLISHER_SQL = "SELECT * FROM publisher";

		Class.forName("com.mysql.cj.jdbc.Driver");

		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment3", "root",
				"root"); Statement selectStmt = connection.createStatement();) {
			
			ResultSet rs = selectStmt.executeQuery(SELECT_PUBLISHER_SQL);
			while (rs.next()) {
				PublisherClass_H publisherClass_H = new PublisherClass_H();
				publisherClass_H.setPubId(rs.getLong("pubId"));
				publisherClass_H.setName(rs.getString("name"));
				publisherClass_H.setAddress(rs.getString("address"));
				publisherList.add(publisherClass_H);
			}
			selectStmt.close();
		}

		catch (SQLException e) {
			printSQLException(e); // calling printSQLException function...
		}

		return publisherList;
	}

}
