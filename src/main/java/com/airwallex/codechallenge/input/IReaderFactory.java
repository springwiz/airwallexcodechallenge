/**
 *
 */
package com.airwallex.codechallenge.input;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating IReader objects.
 */
public interface IReaderFactory {

	/**
	 * New reader.
	 *
	 * @param inputType the input type
	 * @return the reader
	 */
	Reader newReader(ReaderType inputType);
}
