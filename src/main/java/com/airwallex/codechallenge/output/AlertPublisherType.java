/**
 *
 */
package com.airwallex.codechallenge.output;

// TODO: Auto-generated Javadoc
/**
 * The Enum AlertPublisherType.
 */
public enum AlertPublisherType {
	
	/** The file. */
	FILE("file");

	/** The str value. */
	private String strValue;

	
	/**
	 * Instantiates a new alert publisher type.
	 *
	 * @param strValue the str value
	 */
	private AlertPublisherType(String strValue) {
		this.strValue = strValue;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return strValue;
	}
}
