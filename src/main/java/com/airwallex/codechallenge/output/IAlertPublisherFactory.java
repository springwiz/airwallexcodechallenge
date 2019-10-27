/**
 *
 */
package com.airwallex.codechallenge.output;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating IAlertPublisher objects.
 */
public interface IAlertPublisherFactory {

	/**
	 * New alert publisher.
	 *
	 * @param inputType the input type
	 * @return the alert publisher
	 */
	AlertPublisher newAlertPublisher(AlertPublisherType inputType);
}
