/**
 * 
 */
package com.airwallex.codechallenge.output;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.airwallex.codechallenge.model.Alert;

/**
 * @author sumitnarayan
 *
 */
class FileAlertPublisherTest {
	
	private AlertPublisher publisher = null;
	
	private Alert alert = null; 

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
        publisher = AlertPublisherFactory.getInstance().newAlertPublisher(AlertPublisherType.FILE);
        alert = new Alert();
        alert.setAlert("spotChange");
        alert.setCurrencyPair("CNYAUD");
        alert.setRiseFallSeconds(3450);
        alert.setTimestamp(1554933784.023);
	}

	@Test
	void testPublishAlert() {
		try {
			publisher.publishAlert(alert);
			publisher.cleanup();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
