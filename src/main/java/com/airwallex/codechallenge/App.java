package com.airwallex.codechallenge;

import java.util.List;
import java.util.logging.Logger;

import com.airwallex.codechallenge.input.IReaderFactory;
import com.airwallex.codechallenge.input.Reader;
import com.airwallex.codechallenge.input.ReaderFactory;
import com.airwallex.codechallenge.input.ReaderType;
import com.airwallex.codechallenge.model.CurrencyRate;
import com.airwallex.codechallenge.output.AlertPublisher;
import com.airwallex.codechallenge.output.AlertPublisherFactory;
import com.airwallex.codechallenge.output.AlertPublisherType;
import com.airwallex.codechallenge.output.IAlertPublisherFactory;
import com.airwallex.codechallenge.process.CurrencyRateProcessor;

/**
 * The Class App.
 */
public class App {
	
    private static final Logger log = Logger.getLogger(App.class.getName());

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        IReaderFactory readerFactory = ReaderFactory.getInstance();
        Reader reader = readerFactory.newReader(ReaderType.FILE);
        IAlertPublisherFactory publisherFactory = AlertPublisherFactory.getInstance();
        AlertPublisher publisher = publisherFactory.newAlertPublisher(AlertPublisherType.FILE);
  
        List<CurrencyRate> rates = reader.read(args[0]);
        log.info("File input:"+ args[0]);
        
        CurrencyRateProcessor processor = new CurrencyRateProcessor();
        processor.addPublisher(publisher);
        
        try {
        	CurrencyRate []ratesDummy = {};
			processor.processInput(rates.toArray(ratesDummy));
		} catch (Exception e) {
			log.severe(e.toString());
		}		
    }
}
