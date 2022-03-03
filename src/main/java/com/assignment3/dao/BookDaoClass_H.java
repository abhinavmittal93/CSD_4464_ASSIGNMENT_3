package com.assignment3.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.assignment3.model.BookClass_H;

/**
 * @author Abhinav, Aarti, Samridhi
 * Date - 02 Feb 2022
 * Description - This is a DAO class which handles the Database queries related to books table 
 * and transform the retrieved data and return to BookServlet.
 * 
 */
public class BookDaoClass_H {

	/**
	 * It adds a new record in books table with the details provided by the user.
	 * 
	 * @param bookClass_H
	 * @return
	 * @throws ClassNotFoundException
	 */
	public int addBook(BookClass_H bookClass_H) throws ClassNotFoundException {
		// create sql statement
		String INSERT_BOOK_SQL = "INSERT INTO books (title, author, price, available, pubId) " + "VALUES (?,?,?,?,?);";

		int result = 0;

		Class.forName("com.mysql.cj.jdbc.Driver");

		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment3", "root",
				"root"); PreparedStatement ps = connection.prepareStatement(INSERT_BOOK_SQL)) {

			ps.setString(1, bookClass_H.getTitle());
			ps.setString(2, bookClass_H.getAuthor());
			ps.setDouble(3, bookClass_H.getPrice());
			ps.setString(4, String.valueOf(bookClass_H.getAvailable()));
			ps.setLong(5, bookClass_H.getPublisherClass_H().getPubId());

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
	 * It updates the book details by bookId with the provided details.
	 * 
	 * @param bookClass_H
	 * @return
	 * @throws ClassNotFoundException
	 */
	public int updateBook(BookClass_H bookClass_H) throws ClassNotFoundException {
		// create sql statement
		String UPDATE_BOOK_SQL = "UPDATE books SET title = ?, author = ?, price = ?, available = ?, pubId = ? WHERE bookId = ?";

		int result = 0;

		Class.forName("com.mysql.cj.jdbc.Driver");

		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment3", "root",
				"root"); PreparedStatement ps = connection.prepareStatement(UPDATE_BOOK_SQL)) {

			ps.setString(1, bookClass_H.getTitle());
			ps.setString(2, bookClass_H.getAuthor());
			ps.setDouble(3, bookClass_H.getPrice());
			ps.setString(4, String.valueOf(bookClass_H.getAvailable()));
			ps.setLong(5, bookClass_H.getPublisherClass_H().getPubId());
			ps.setLong(6, bookClass_H.getBookId());

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
	 * It retrieves all the records from books table and set it in the Model and
	 * return the List. Also, it retrieves the foreign key table details as well.
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 */
	public List<BookClass_H> getAllBooks() throws ClassNotFoundException {
		List<BookClass_H> booksList = new ArrayList<BookClass_H>();

		// create sql statement
		String SELECT_BOOKS_SQL = "SELECT * FROM books";

		Class.forName("com.mysql.cj.jdbc.Driver");

		PublisherDaoClass_H publisherDao = new PublisherDaoClass_H();
		MemberDaoClass_H memberDao = new MemberDaoClass_H();

		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment3", "root",
				"root"); Statement selectStmt = connection.createStatement();) {

			ResultSet rs = selectStmt.executeQuery(SELECT_BOOKS_SQL);
			while (rs.next()) {
				BookClass_H bookClass_H = new BookClass_H();
				bookClass_H.setBookId(rs.getLong("bookId"));
				bookClass_H.setTitle(rs.getString("title"));
				bookClass_H.setAuthor(rs.getString("author"));
				bookClass_H.setAvailable(rs.getString("available").charAt(0));
				bookClass_H.setPrice(rs.getDouble("price"));
				bookClass_H.setPublisherClass_H(publisherDao.getPublisherDetails(rs.getLong("pubId")));
				if (rs.getLong("membId") > 0) {
					bookClass_H.setMemberClass_H(memberDao.getMemberDetails(rs.getLong("membId")));
				}
				bookClass_H.setIssueDate(rs.getDate("issueDate"));
				bookClass_H.setDueDate(rs.getDate("dueDate"));
				bookClass_H.setReturnDate(rs.getDate("returnDate"));
				booksList.add(bookClass_H);
			}
			selectStmt.close();
		}

		catch (SQLException e) {
			printSQLException(e); // calling printSQLException function...
		}

		return booksList;
	}

	/**
	 * It issues a book to a member by adding membId to the books table and setting
	 * it's availability to 'N'.
	 * 
	 * @param bookClass_H
	 * @return
	 * @throws ClassNotFoundException
	 */
	public int issueBook(BookClass_H bookClass_H) throws ClassNotFoundException {
		// create sql statement
		String ISSUE_BOOK_SQL = "UPDATE books SET membId = ?, issueDate = ?, dueDate = ?, available = ? WHERE bookId = ?";

		int result = 0;

		Class.forName("com.mysql.cj.jdbc.Driver");

		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment3", "root",
				"root"); PreparedStatement ps = connection.prepareStatement(ISSUE_BOOK_SQL)) {

			ps.setLong(1, bookClass_H.getMemberClass_H().getMembId());
			ps.setDate(2, bookClass_H.getIssueDate());
			ps.setDate(3, bookClass_H.getDueDate());
			ps.setString(4, "N");
			ps.setLong(5, bookClass_H.getBookId());

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
	 * It deletes the boom record from books table by bookId.
	 * 
	 * @param bookId
	 * @return
	 * @throws ClassNotFoundException
	 */
	public int deleteBook(Long bookId) throws ClassNotFoundException {
		// create sql statement
		String DELETE_BOOK_SQL = "DELETE from books WHERE bookId = " + bookId;

		int result = 0;

		Class.forName("com.mysql.cj.jdbc.Driver");

		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment3", "root",
				"root"); Statement stmt = connection.createStatement();) {

			result = stmt.executeUpdate(DELETE_BOOK_SQL);
			stmt.close();
		}

		catch (SQLException e) {
			printSQLException(e); // calling printSQLException function...
		}
		return result;
	}
	
	/**
	 * It gets the List of books which are currently borrowed or were borrowed in the past.
	 * 
	 * @return the list of books borrowed
	 * @throws ClassNotFoundException
	 */
	public List<BookClass_H> getIssuedBooks() throws ClassNotFoundException {
		List<BookClass_H> booksList = new ArrayList<BookClass_H>();

		// create sql statement
		String SELECT_BOOKS_SQL = "SELECT * FROM books where (returnDate IS NULL AND available = 'N') "
				+ "OR (returnDate IS NOT NULL AND available = 'Y')";

		Class.forName("com.mysql.cj.jdbc.Driver");

		PublisherDaoClass_H publisherDao = new PublisherDaoClass_H();
		MemberDaoClass_H memberDao = new MemberDaoClass_H();

		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment3", "root",
				"root"); Statement selectStmt = connection.createStatement();) {

			ResultSet rs = selectStmt.executeQuery(SELECT_BOOKS_SQL);
			while (rs.next()) {
				BookClass_H bookClass_H = new BookClass_H();
				bookClass_H.setBookId(rs.getLong("bookId"));
				bookClass_H.setTitle(rs.getString("title"));
				bookClass_H.setAuthor(rs.getString("author"));
				bookClass_H.setAvailable(rs.getString("available").charAt(0));
				bookClass_H.setPrice(rs.getDouble("price"));
				bookClass_H.setPublisherClass_H(publisherDao.getPublisherDetails(rs.getLong("pubId")));
				if (rs.getLong("membId") > 0) {
					bookClass_H.setMemberClass_H(memberDao.getMemberDetails(rs.getLong("membId")));
				}
				bookClass_H.setIssueDate(rs.getDate("issueDate"));
				bookClass_H.setDueDate(rs.getDate("dueDate"));
				bookClass_H.setReturnDate(rs.getDate("returnDate"));
				booksList.add(bookClass_H);
			}
			selectStmt.close();
		}

		catch (SQLException e) {
			printSQLException(e); // calling printSQLException function...
		}

		return booksList;
	}

}
