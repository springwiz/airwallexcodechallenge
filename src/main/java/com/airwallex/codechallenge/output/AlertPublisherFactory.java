/**
 *
 */
package com.airwallex.codechallenge.output;

import java.io.IOException;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Readers.
 *
 * @author sumit
 */
public class AlertPublisherFactory implements IAlertPublisherFactory {

	/** The alert publisher factory. */
	private static AlertPublisherFactory alertPublisherFactory = new AlertPublisherFactory();

	/**
	 * Gets the single instance of AlertPublisherFactory.
	 *
	 * @return single instance of AlertPublisherFactory
	 */
	public static IAlertPublisherFactory getInstance() {
		return alertPublisherFactory;
	}

	/**
	 * Instantiates a new alert publisher factory.
	 */
	private AlertPublisherFactory() {

	}

	/**
	 * New alert publisher.
	 *
	 * @param pubType the pub type
	 * @return the alert publisher
	 */
	@Override
	public AlertPublisher newAlertPublisher(AlertPublisherType pubType) {
		AlertPublisher publisher = null;
		switch (pubType) {
		case FILE:
			try {
				publisher = new FileAlertPublisher();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		return publisher;
	}
}