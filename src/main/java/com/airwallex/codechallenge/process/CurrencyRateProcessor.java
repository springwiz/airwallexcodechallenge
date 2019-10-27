/**
 * 
 */
package com.airwallex.codechallenge.process;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import com.airwallex.codechallenge.model.Alert;
import com.airwallex.codechallenge.model.CurrencyRate;
import com.airwallex.codechallenge.output.AlertPublisher;

/**
 * The Class CurrencyRateProcessor.
 *
 * @author sumitnarayan
 */
public class CurrencyRateProcessor {

	/** The Constant log. */
	private static final Logger log = Logger.getLogger(CurrencyRateProcessor.class.getName());
	
	/**
	 * The Class CurrencyRateInput.
	 */
	private class CurrencyRateInput {
		
		/** The total rates. */
		private double totalRates;
		
		/** The Constant MAX_SIZE. */
		private static final int MAX_SIZE = 300;
		
		/** The rate buffer. */
		private PriorityBlockingQueue<CurrencyRate> rateBuffer = null;
		
		/** The previous rate. */
		private CurrencyRate previousRate = null;
		
		/** The falling for. */
		private long fallingFor = 0;
		
		/** The rising for. */
		private long risingFor = 0;
		
		/** The loop counter. */
		private long loopCounter = 0;
		
		/**
		 * Instantiates a new currency rate input.
		 *
		 * @param currencyPair the currency pair
		 * @param rateBuffer the rate buffer
		 */
		public CurrencyRateInput(String currencyPair, PriorityBlockingQueue<CurrencyRate> rateBuffer) {
			this.rateBuffer = rateBuffer;
		}
	}
	
	/** The pub list. */
	private List<AlertPublisher> pubList = new ArrayList<>();
	
	/** The data map. */
	private ConcurrentHashMap<String, CurrencyRateInput> dataMap = new ConcurrentHashMap<>();
	
	/** The pool. */
	private ExecutorService pool = Executors.newCachedThreadPool();
	
	/** The currency rates pairs. */
	private List<String> currencyRatesPairs = new ArrayList<>(); {
		currencyRatesPairs.add("CNYAUD");
		currencyRatesPairs.add("USDAUD");
		currencyRatesPairs.add("CNYAUD");
	};
	
	/**
	 * Process input.
	 *
	 * @param rates the rates
	 * @throws Exception the exception
	 */
	public void processInput(CurrencyRate ...rates) throws Exception {
		log.finer("Input file size: "+rates.length);
		currencyRatesPairs.forEach(currencyRatesPair -> dataMap.put(currencyRatesPair, new CurrencyRateInput(currencyRatesPair, 
				new PriorityBlockingQueue<CurrencyRate>(CurrencyRateInput.MAX_SIZE))));
		pool.submit(() -> {
			CurrencyRateInput input = null;
			for(CurrencyRate currentRate : rates) {
				input = dataMap.get(currentRate.getCurrencyPair());
				if(input.loopCounter > 60) {
					log.info("loopCounter: "+input.loopCounter);
					log.info("risingFor: "+input.risingFor);
					log.info("fallingFor: "+input.fallingFor);
					if(input.risingFor >= 900) {
						Alert alert = new Alert();
						alert.setCurrencyPair(currentRate.getCurrencyPair());
						alert.setTimestamp(currentRate.getTimestamp());
						alert.setAlert("rising");
						alert.setRiseFallSeconds(input.risingFor);
						raiseAlert(alert);
						input.loopCounter = 0;
						
					} else if(input.fallingFor >= 900) {
						Alert alert = new Alert();
						alert.setCurrencyPair(currentRate.getCurrencyPair());
						alert.setTimestamp(currentRate.getTimestamp());
						alert.setAlert("falling");
						alert.setRiseFallSeconds(input.fallingFor);
						raiseAlert(alert);
						input.loopCounter = 0;
					}
				}
				if(input.previousRate != null) {
					if(currentRate.getRate() > input.previousRate.getRate()) {
						input.risingFor++;
						input.fallingFor = 0;			
					}
					else if(currentRate.getRate() < input.previousRate.getRate()) {
						input.fallingFor++;
						input.risingFor = 0;
					}
					else {
						input.risingFor = 0;
						input.fallingFor = 0;
					}
				}			
				log.info("rateBuffer size: "+input.rateBuffer.size());
				if(input.rateBuffer.size() == CurrencyRateInput.MAX_SIZE) {
					if(verifyConditionAlert1(currentRate)) {
						Alert alert = new Alert();
						alert.setAlert("spotChange");
						alert.setCurrencyPair(currentRate.getCurrencyPair());
						alert.setTimestamp(currentRate.getTimestamp());
						raiseAlert(alert);
					}
					CurrencyRate oldRate = null;
					try {
						oldRate = input.rateBuffer.take();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					input.totalRates -= oldRate.getRate();
				}
				input.rateBuffer.put(currentRate);
				input.totalRates += currentRate.getRate();
				input.previousRate = currentRate;
				input.loopCounter++;
			}
		});
		pool.shutdown();
		pool.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
		pubList.forEach(t -> {
			try {
				t.cleanup();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	
	/**
	 * Adds the publisher.
	 *
	 * @param publisher the publisher
	 */
	public void addPublisher(AlertPublisher publisher) {
		pubList.add(publisher);
	}
	
	/**
	 * Raise alert.
	 *
	 * @param alert the alert
	 */
	private void raiseAlert(Alert alert) {
		pubList.forEach(t -> {
			try {
				t.publishAlert(alert);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	
	/**
	 * Verify condition alert 1.
	 *
	 * @param currentRate the current rate
	 * @return true, if successful
	 */
	private boolean verifyConditionAlert1(CurrencyRate currentRate) {
		CurrencyRateInput input = dataMap.get(currentRate.getCurrencyPair());
		double eventThreshold = 0.1 * input.totalRates/CurrencyRateInput.MAX_SIZE;
		double rateDiff = Math.abs(currentRate.getRate() - input.rateBuffer.peek().getRate());
		return (rateDiff > eventThreshold);
	}
}
