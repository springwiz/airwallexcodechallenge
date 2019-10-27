package com.airwallex.codechallenge.output;

import java.util.List;

import com.airwallex.codechallenge.model.Alert;

/**
 * The Interface AlertPublisher.
 */
public interface AlertPublisher {

	/**
	 * Publish alert.
	 *
	 * @param alert the alert
	 * @throws Exception the exception
	 */
	void publishAlert(Alert alert) throws Exception;
	
	/**
	 * Cleanup.
	 */
	void cleanup();
	
	/**
	 * Gets the alert cache.
	 *
	 * @return the alert cache
	 */
	List<Alert> getAlertCache();

}