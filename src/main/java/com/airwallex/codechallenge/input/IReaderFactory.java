/**
 *
 */
package com.airwallex.codechallenge.input;

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
