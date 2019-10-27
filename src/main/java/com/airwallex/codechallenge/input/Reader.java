package com.airwallex.codechallenge.input;

import java.util.List;

import com.airwallex.codechallenge.model.CurrencyRate;

// TODO: Auto-generated Javadoc
/**
 * The Interface Reader.
 */
public interface Reader {

	/**
	 * Read.
	 *
	 * @param string the string
	 * @return the list
	 */
	List<CurrencyRate> read(String string);

}
