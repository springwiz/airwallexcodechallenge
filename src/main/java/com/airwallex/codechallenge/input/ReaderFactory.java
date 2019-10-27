/**
 *
 */
package com.airwallex.codechallenge.input;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Readers.
 *
 * @author sumit
 */
public class ReaderFactory implements IReaderFactory {

	/** The reader factory. */
	private static ReaderFactory readerFactory = new ReaderFactory();

	/**
	 * Gets the single instance of ReaderFactory.
	 *
	 * @return single instance of ReaderFactory
	 */
	public static IReaderFactory getInstance() {
		return readerFactory;
	}

	/**
	 * Instantiates a new reader factory.
	 */
	private ReaderFactory() {

	}

	/**
	 * New reader.
	 *
	 * @param inputType the input type
	 * @return the reader
	 */
	@Override
	public Reader newReader(ReaderType inputType) {
		Reader reader = null;
		switch (inputType) {
		case FILE:
			reader = new CurrencyRateReader();
			break;
		}
		return reader;
	}
}