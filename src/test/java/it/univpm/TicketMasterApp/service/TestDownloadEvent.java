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

import it.univpm.TicketMasterApp.model.Eventi;

/**
 * @author Elisa Pace
 * @author Marco Vassallo
 *
 */
class TestDownloadEvent {
	Vector<Eventi> eventi = null;
	String stateCode;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		DownloadEvent e = new DownloadEvent();
		eventi = new Vector<Eventi>();
		String stateCode = "AB";
		e.EventiInfo(stateCode);
		eventi = e.getStrutturaDati();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("Corretto funzionamento di DownloadEvent.")
	void test() {
		assertNotNull(eventi);
	}

}
