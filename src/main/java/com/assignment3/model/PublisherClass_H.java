/**
 * 
 */
package com.assignment3.model;

/**
 * @author Navdeep, Syed Ahmed 
 * Date - 02 Feb 2022
 * Description - Model class PublisherClass_H for table publisher with pubId, name, address.
 *
 */
public class PublisherClass_H {

	private Long pubId;

	private String name;

	private String address;

	/**
	 * Default Constructor
	 */
	public PublisherClass_H() {
	}

	/**
	 * @param pubId
	 * @param name
	 * @param address
	 */
	public PublisherClass_H(Long pubId, String name, String address) {
		this.pubId = pubId;
		this.name = name;
		this.address = address;
	}

	/**
	 * @return the pubId
	 */
	public Long getPubId() {
		return pubId;
	}

	/**
	 * @param pubId the pubId to set
	 */
	public void setPubId(Long pubId) {
		this.pubId = pubId;
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

}
