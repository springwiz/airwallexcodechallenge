/**
 * 
 */
package com.airwallex.codechallenge.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.text.DecimalFormat;

// TODO: Auto-generated Javadoc
/**
 * The Class Alert.
 *
 * @author sumitnarayan
 */
public class Alert {
	
	/** The alert. */
	private String alert;
	
	/** The timestamp. */
	private double timestamp;
	 
	/** The currency pair. */
	private String currencyPair;
	
	/** The rise fall seconds. */
	private long riseFallSeconds;
	
	/**
	 * Gets the alert.
	 *
	 * @return the alert
	 */
	@JsonProperty
	public String getAlert() {
		return alert;
	}

	/**
	 * Sets the alert.
	 *
	 * @param alert the alert to set
	 */
	public void setAlert(String alert) {
		this.alert = alert;
	}

	/**
	 * Gets the timestamp.
	 *
	 * @return the timestamp
	 */
	@JsonProperty
	public double getTimestamp() {
		return timestamp;
	}

	/**
	 * Sets the timestamp.
	 *
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(double timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * Gets the currency pair.
	 *
	 * @return the currencyPair
	 */
	@JsonProperty
	public String getCurrencyPair() {
		return currencyPair;
	}

	/**
	 * Sets the currency pair.
	 *
	 * @param currencyPair the currencyPair to set
	 */
	public void setCurrencyPair(String currencyPair) {
		this.currencyPair = currencyPair;
	}

	/**
	 * Gets the rise fall seconds.
	 *
	 * @return the riseFallSeconds
	 */
	@JsonProperty("seconds")
	public long getRiseFallSeconds() {
		return riseFallSeconds;
	}

	/**
	 * Sets the rise fall seconds.
	 *
	 * @param riseFallSeconds the riseFallSeconds to set
	 */
	public void setRiseFallSeconds(long riseFallSeconds) {
		this.riseFallSeconds = riseFallSeconds;
	}
}
