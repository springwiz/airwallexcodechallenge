package com.airwallex.codechallenge.input;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.airwallex.codechallenge.model.CurrencyRate;

class CurrencyRateReaderTest {

	private Reader reader = null;
	
	@BeforeEach
	void setUp() throws Exception {
        reader = ReaderFactory.getInstance().newReader(ReaderType.FILE);
	}

	@Test
	void testRead() {
        List<CurrencyRate> inputList = reader.read("src/main/data/currencyrates");
        assert inputList.size() > 0;
	}
}
