package quiz;

import static org.junit.Assert.*;
import org.junit.Test;

public class JUnitTests {

	@Test
	public void testOne() {
		int output = Controller.numOfAttempts;
		assertEquals(0, output);
	}
	
	@Test
	public void testTwo() {
		int output = Controller.defaultNumOfAttempts;
		assertEquals(0, output);
	}
	
	@Test
	public void testThree() {
		int output = Controller.questionNum;
		assertEquals(0, output);
	}

}