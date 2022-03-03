/**
 * 
 */
package com.assignment3.model;

import java.sql.Date;

/**
 * @author Abhinav, Samridhi, Aarti
 * Date - 02 Feb 2022
 * Description - Model class BookClass_H for table Books with bookId, title, author, price, available, 
 * PublisherClass_H, MemberClass_H, issueDate, dueDate, returnDate.
 *
 */
public class BookClass_H {

	private Long bookId;

	private String title;

	private String author;

	private double price;

	private char available;

	private PublisherClass_H publisherClass_H;

	private MemberClass_H memberClass_H;

	private Date issueDate;

	private Date dueDate;

	private Date returnDate;

	/**
	 * @return the bookId
	 */
	public Long getBookId() {
		return bookId;
	}

	/**
	 * @param bookId the bookId to set
	 */
	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the available
	 */
	public char getAvailable() {
		return available;
	}

	/**
	 * @param available the available to set
	 */
	public void setAvailable(char available) {
		this.available = available;
	}

	/**
	 * @return the publisherClass_H
	 */
	public PublisherClass_H getPublisherClass_H() {
		return publisherClass_H;
	}

	/**
	 * @param publisherClass_H the publisherClass_H to set
	 */
	public void setPublisherClass_H(PublisherClass_H publisherClass_H) {
		this.publisherClass_H = publisherClass_H;
	}

	/**
	 * @return the memberClass_H
	 */
	public MemberClass_H getMemberClass_H() {
		return memberClass_H;
	}

	/**
	 * @param memberClass_H the memberClass_H to set
	 */
	public void setMemberClass_H(MemberClass_H memberClass_H) {
		this.memberClass_H = memberClass_H;
	}

	/**
	 * @return the issueDate
	 */
	public Date getIssueDate() {
		return issueDate;
	}

	/**
	 * @param issueDate the issueDate to set
	 */
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	/**
	 * @return the dueDate
	 */
	public Date getDueDate() {
		return dueDate;
	}

	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	/**
	 * @return the returnDate
	 */
	public Date getReturnDate() {
		return returnDate;
	}

	/**
	 * @param returnDate the returnDate to set
	 */
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

}
