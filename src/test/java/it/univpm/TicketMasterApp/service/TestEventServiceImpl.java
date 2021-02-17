/**
 * 
 */
package it.univpm.TicketMasterApp.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import it.univpm.TicketMasterApp.exception.WrongStateCodeException;

/** 
 * @author Elisa Pace
 * @author Marco Vassallo
 *
 */
class TestEventServiceImpl {
	
    String stateCode;
	private EventServiceImpl event;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		event = new EventServiceImpl();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

    @Test
	@DisplayName("Corretta generazione dell'eccezione WrongStateCodeException.")
	 void test() {
		stateCode = "FG";
		assertThrows(WrongStateCodeException.class, ()-> event.getPromoter(stateCode));
	}

}
