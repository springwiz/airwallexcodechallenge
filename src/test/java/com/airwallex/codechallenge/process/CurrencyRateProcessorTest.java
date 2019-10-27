/**
 * 
 */
package com.airwallex.codechallenge.process;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.airwallex.codechallenge.model.Alert;
import com.airwallex.codechallenge.model.CurrencyRate;
import com.airwallex.codechallenge.output.AlertPublisher;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author sumitnarayan
 *
 */
class CurrencyRateProcessorTest {

	FileWriter fw = null;
	
	/** The object mapper. */
	private ObjectMapper objectMapper = null;
	
	private BufferedReader reader = null;
	
	private CurrencyRateProcessor processor = null;
	
	private AlertPublisher publisher = null;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() {
		processor = new CurrencyRateProcessor();
        objectMapper = new ObjectMapper();
	}

	@Test
	void testProcessInput() {
		publisher = newAlertPublisherForTest();
		try {
			List<CurrencyRate> arr = Files.readAllLines(Paths.get("src/main/data/events")).stream().map(json -> {
				CurrencyRate rate = null;
				try {
					rate = objectMapper.readValue(json, CurrencyRate.class);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return rate;
			}).collect(Collectors.toList());
			CurrencyRate[] dummy = {};
			processor.addPublisher(publisher);
			processor.processInput(arr.toArray(dummy));
			assert(publisher.getAlertCache().size() > 0);
			assert(publisher.getAlertCache().get(0).getAlert().equalsIgnoreCase("spotChange"));
		} catch (Exception e) {
			e.printStackTrace();
		}
			}
	
	@Test
	void testProcessInput1() {
		AlertPublisher publisher = newAlertPublisherForTest();
		try {
			List<CurrencyRate> arr = Files.readAllLines(Paths.get("src/main/data/events1")).stream().map(json -> {
				CurrencyRate rate = null;
				try {
					rate = objectMapper.readValue(json, CurrencyRate.class);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return rate;
			}).collect(Collectors.toList());
			CurrencyRate[] dummy = {};
			processor.addPublisher(publisher);
			processor.processInput(arr.toArray(dummy));
			assert(publisher.getAlertCache().size() > 0);
			assert(publisher.getAlertCache().get(0).getAlert().equalsIgnoreCase("rising"));
			assert(publisher.getAlertCache().get(0).getRiseFallSeconds() == 900);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			}
	
	/*@Test
	void test1() throws Exception {
		//{ "timestamp": 1554933784.087, "currencyPair": "CNYAUD", "rate": 0.85641 }
		fw = new FileWriter("src/main/data/events1");
		CurrencyRate rate = new CurrencyRate();
		rate.setTimestampS(1554933784.087);
		rate.setCurrencyPair("CNYAUD");
		rate.setRate(0.85641);
		for(int i = 0;i<901;i++) {
			String eventString = objectMapper.writeValueAsString(rate);
			fw.write(eventString + "\n");
			rate.setTimestampS(rate.getTimestampS() + 1.0);
			rate.setCurrencyPair("CNYAUD");
			rate.setRate(rate.getRate() + 0.00001);	
		}
		fw.close();
	}*/
	
	private AlertPublisher newAlertPublisherForTest() {
		return new AlertPublisher() {
			List<Alert> list = new ArrayList<>();
			
			@Override
			public void publishAlert(Alert alert) throws Exception {
				list.add(alert);
			}

			@Override
			public void cleanup() {
			}

			@Override
			public List<Alert> getAlertCache() {
				return list;
			}		
		};
	}
}
