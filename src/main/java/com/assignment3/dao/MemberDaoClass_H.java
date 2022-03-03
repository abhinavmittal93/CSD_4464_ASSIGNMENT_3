package com.assignment3.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.assignment3.model.MemberClass_H;

/**
 * @author Abhinav, Aarti, Samridhi
 * Date - 02 Feb 2022
 * Description - This is a DAO class which handles the Database queries related to member table 
 * and transform the retrieved data and return to MemberServlet.
 * 
 */
public class MemberDaoClass_H {

	/**
	 * It adds a new record in member table with the details provided by the user.
	 * 
	 * @param member
	 * @return
	 * @throws ClassNotFoundException
	 */
	public int registerMember(MemberClass_H member) throws ClassNotFoundException {
		// create sql statement
		String INSERT_MEMBER_SQL = "INSERT INTO member (name, address, membType, membDate, expiryDate) "
				+ "VALUES (?,?,?,?,?);";

		int result = 0;

		Class.forName("com.mysql.cj.jdbc.Driver");

		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment3", "root",
				"root"); PreparedStatement ps = connection.prepareStatement(INSERT_MEMBER_SQL)) {

			ps.setString(1, member.getName());
			ps.setString(2, member.getAddress());
			ps.setString(3, member.getMembType());
			ps.setDate(4, member.getMembDate());
			ps.setDate(5, member.getExpiryDate());

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
	 * It updates the member details by membId with the provided details.
	 * 
	 * @param member
	 * @return
	 * @throws ClassNotFoundException
	 */
	public int updateMember(MemberClass_H member) throws ClassNotFoundException {

		// create sql statement
		String UPDATE_MEMBER_SQL = "UPDATE member SET name = ?, address = ?, membType = ? WHERE membId = ?";

		int result = 0;

		Class.forName("com.mysql.cj.jdbc.Driver");

		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment3", "root",
				"root"); PreparedStatement ps = connection.prepareStatement(UPDATE_MEMBER_SQL)) {

			ps.setString(1, member.getName());
			ps.setString(2, member.getAddress());
			ps.setString(3, member.getMembType());
			ps.setLong(4, member.getMembId());

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
	 * It retrieves the member details by membId, from member table and set it in the Model and return it.
	 * 
	 * @param membId
	 * @return
	 * @throws ClassNotFoundException
	 */
	public MemberClass_H getMemberDetails(long membId) throws ClassNotFoundException {
		MemberClass_H memberClass_H = new MemberClass_H();
		// create sql statement
		String SELECT_MEMBER_SQL = "SELECT * FROM member WHERE membId = " + membId;

		Class.forName("com.mysql.cj.jdbc.Driver");

		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment3", "root",
				"root"); Statement selectStmt = connection.createStatement();) {
			
			ResultSet rs = selectStmt.executeQuery(SELECT_MEMBER_SQL);
			while (rs.next()) {
				memberClass_H.setMembId(rs.getLong("membId"));
				memberClass_H.setName(rs.getString("name"));
				memberClass_H.setAddress(rs.getString("address"));
				memberClass_H.setMembType(rs.getString("membType"));
				memberClass_H.setMembDate(rs.getDate("membDate"));
				memberClass_H.setExpiryDate(rs.getDate("expiryDate"));
			}
			selectStmt.close();
		}

		catch (SQLException e) {
			printSQLException(e); // calling printSQLException function...
		}

		return memberClass_H;
	}
	
	/**
	 * It retrieves all the records from member table and set it in the Model and return the List.
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 */
	public List<MemberClass_H> getAllMembers() throws ClassNotFoundException {
		List<MemberClass_H> memberClassList = new ArrayList<MemberClass_H>();
		
		// create sql statement
		String SELECT_MEMBER_SQL = "SELECT * FROM member";

		Class.forName("com.mysql.cj.jdbc.Driver");

		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment3", "root",
				"root"); Statement selectStmt = connection.createStatement();) {
			
			ResultSet rs = selectStmt.executeQuery(SELECT_MEMBER_SQL);
			while (rs.next()) {
				MemberClass_H memberClass_H = new MemberClass_H();
				memberClass_H.setMembId(rs.getLong("membId"));
				memberClass_H.setName(rs.getString("name"));
				memberClass_H.setAddress(rs.getString("address"));
				memberClass_H.setMembType(rs.getString("membType"));
				memberClass_H.setMembDate(rs.getDate("membDate"));
				memberClass_H.setExpiryDate(rs.getDate("expiryDate"));
				memberClassList.add(memberClass_H);
			}
			selectStmt.close();
		}

		catch (SQLException e) {
			printSQLException(e); // calling printSQLException function...
		}

		return memberClassList;
	}

}
