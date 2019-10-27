/**
 * 
 */
package com.airwallex.codechallenge.output;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.logging.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.airwallex.codechallenge.App;
import com.airwallex.codechallenge.model.Alert;

/**
 * The Class FileAlertPublisher.
 *
 * @author sumitnarayan
 */
public class FileAlertPublisher implements AlertPublisher {

	/** The alerts file. */
	private FileWriter alertsFile = null;
	
	/** The object mapper. */
	private ObjectMapper objectMapper = null;
	
	private int alertCount = 0;
	
    private static final Logger log = Logger.getLogger(FileAlertPublisher.class.getName());
	
	/**
	 * Instantiates a new file alert publisher.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	FileAlertPublisher() throws IOException {
		alertsFile = new FileWriter("Alerts", Charset.defaultCharset());
		objectMapper = new ObjectMapper();
	}
	
	/**
	 * Publish alert.
	 *
	 * @param alert the alert
	 * @throws Exception the exception
	 */
	@Override
	public void publishAlert(Alert alert) throws Exception {
		String alertString = objectMapper.writeValueAsString(alert);
		alertsFile.write(alertString+"\n");
		setAlertCount(getAlertCount() + 1);
	}
	
	/**
	 * Cleanup.
	 */
	public void cleanup() {
		try {
			alertsFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the alertCount
	 */
	public int getAlertCount() {
		return alertCount;
	}

	/**
	 * @param alertCount the alertCount to set
	 */
	public void setAlertCount(int alertCount) {
		this.alertCount = alertCount;
	}

	@Override
	public List<Alert> getAlertCache() {
		return null;
	}
}
