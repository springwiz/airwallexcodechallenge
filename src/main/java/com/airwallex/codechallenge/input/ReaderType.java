/**
 *
 */
package com.airwallex.codechallenge.input;

// TODO: Auto-generated Javadoc
/**
 * The Enum ReaderType.
 */
public enum ReaderType {
	
	/** The file. */
	FILE("file");

	/** The str value. */
	private String strValue;

	
	/**
	 * Instantiates a new reader type.
	 *
	 * @param strValue the str value
	 */
	private ReaderType(String strValue) {
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
