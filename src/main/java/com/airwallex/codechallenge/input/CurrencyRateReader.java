/**
 * 
 */
package com.airwallex.codechallenge.input;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.airwallex.codechallenge.App;
import com.airwallex.codechallenge.model.CurrencyRate;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The Class CurrencyRateReader.
 *
 * @author sumitnarayan
 */
public class CurrencyRateReader implements Reader {
	
    private static final Logger log = Logger.getLogger(CurrencyRateReader.class.getName());

	/**
	 * Read.
	 *
	 * @param filePath the file path
	 * @return the list
	 */
	@Override
	public List<CurrencyRate> read(String filePath) {
		Stream<CurrencyRate> lines = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			String[] tokens = filePath.split(System.lineSeparator());
			lines = Files
					.readAllLines(Paths.get(filePath.replace(tokens[tokens.length - 1], ""), 
						tokens[tokens.length - 1]), Charset.defaultCharset())
					.parallelStream().map(json -> {
						CurrencyRate rate = null;
						try {
							rate = objectMapper.readValue(json, CurrencyRate.class);
						} catch (Exception e) {
							e.printStackTrace();
						}
						return rate;
					});

		} catch (final Exception e) {
			e.printStackTrace();
		}
		return lines.collect(Collectors.toList());
	}

}
