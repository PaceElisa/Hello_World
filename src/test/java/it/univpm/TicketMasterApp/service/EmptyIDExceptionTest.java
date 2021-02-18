/**
 * 
 */
package it.univpm.TicketMasterApp.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Vector;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import it.univpm.TicketMasterApp.exception.EmptyIDException;

/**
 * @author Elisa Pace
 * @author Marco Vassallo
 * Questa classe si occupa della corretta gestione della classe StatsPromoter
 */
class EmptyIDExceptionTest {

	Vector<String> promoterbody;
	EventServiceImpl service;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		promoterbody = new Vector<>();
		service = new EventServiceImpl();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("Corretto funzionamento dell'Eccezione EmptyIDException.")
	void test() {
		promoterbody.add("850");
		promoterbody.add(null);
		assertThrows(EmptyIDException.class, ()-> service.StatsPromoter(promoterbody));
	}

}
