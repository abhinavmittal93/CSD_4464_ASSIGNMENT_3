/**
 * 
 */
package com.assignment3.model;

import java.sql.Date;

/**
 * @author Aarti, Samridhi
 * Date - 02 Feb 2022
 * Description - Model class MemberClass_H for table member with membId, name, address, membType, membDate, expiryDate.
 *
 */
public class MemberClass_H {

	private Long membId;

	private String name;

	private String address;

	private String membType;

	private Date membDate;

	private Date expiryDate;

	/**
	 * Default Constructor
	 */
	public MemberClass_H() {
	}

	/**
	 * @param membId
	 * @param name
	 * @param address
	 * @param membType
	 * @param membDate
	 * @param expiryDate
	 */
	public MemberClass_H(Long membId, String name, String address, String membType, Date membDate, Date expiryDate) {
		this.membId = membId;
		this.name = name;
		this.address = address;
		this.membType = membType;
		this.membDate = membDate;
		this.expiryDate = expiryDate;
	}

	/**
	 * @return the membId
	 */
	public Long getMembId() {
		return membId;
	}

	/**
	 * @param membId the membId to set
	 */
	public void setMembId(Long membId) {
		this.membId = membId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the membType
	 */
	public String getMembType() {
		return membType;
	}

	/**
	 * @param membType the membType to set
	 */
	public void setMembType(String membType) {
		this.membType = membType;
	}

	/**
	 * @return the membDate
	 */
	public Date getMembDate() {
		return membDate;
	}

	/**
	 * @param membDate the membDate to set
	 */
	public void setMembDate(Date membDate) {
		this.membDate = membDate;
	}

	/**
	 * @return the expiryDate
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}

	/**
	 * @param expiryDate the expiryDate to set
	 */
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

}
