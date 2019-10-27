/**
 * 
 */
package com.airwallex.codechallenge.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class CurrencyRate.
 *
 * @author sumitnarayan
 */
public class CurrencyRate implements Comparable<CurrencyRate> {
	 /** The timestamp. */
 	private long timestamp;
 	
 	private double timestampS;

	/** The currency pair. */
 	private String currencyPair;
	 
	 /** The rate. */
 	private double rate;

 	@JsonProperty("timestamp")
 	public double getTimestampS() {
 		return timestampS;
 	}

 	public void setTimestampS(double timestampS) {
 		this.timestampS = timestampS;
 		this.timestamp = (long)this.timestampS * 1000;
 	}
 	
 	/**
	 * Gets the timestamp.
	 *
	 * @return the timestamp
	 */
	@JsonIgnore
	public long getTimestamp() {
		return timestamp;
	}

	/**
	 * Sets the timestamp.
	 *
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
		this.timestampS = this.timestamp / 1000;
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
	 * Gets the rate.
	 *
	 * @return the rate
	 */
	@JsonProperty
	public double getRate() {
		return rate;
	}

	/**
	 * Sets the rate.
	 *
	 * @param rate the rate to set
	 */
	public void setRate(double rate) {
		this.rate = rate;
	}

	/**
	 * Compare to.
	 *
	 * @param o the o
	 * @return the int
	 */
	@Override
	public int compareTo(CurrencyRate o) {
		return (int) (timestamp - o.timestamp);
	}
}
