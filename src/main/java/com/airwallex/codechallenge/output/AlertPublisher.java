package com.airwallex.codechallenge.output;

import java.util.List;

import com.airwallex.codechallenge.model.Alert;

// TODO: Auto-generated Javadoc
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
	
	void cleanup();
	
	List<Alert> getAlertCache();

}