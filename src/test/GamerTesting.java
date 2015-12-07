package test;
import static org.junit.Assert.*;
import (.Gamer;
import org.junit.Test;


public class GamerTesting {
	
	@Test
	public void testConst() {
		Gamer g = new Gamer("Test", 1, 1, 1.1);
		assertEquals("Test", g.getName());
		assertEquals(1, g.getScore());
		assertEquals(1, g.getJumps());
		assertEquals(1.1, g.getTime(), 0.1);
	}

	@Test
	public void testSetName() {
		Gamer g = new Gamer("Test", 1, 1, 1.1);
		g.setName("NewName");
		assertEquals("NewName", g.getName());
	}
	@Test
	public void testSetScore() {
		Gamer g = new Gamer("Test", 1, 1, 1.1);
		g.setScore(1000);
		assertEquals(1000, g.getScore());
	}
	@Test
	public void testSetJumps() {
		Gamer g = new Gamer("Test", 1, 1, 1.1);
		g.setJumps(1000);
		assertEquals(1000, g.getJumps());
	}
	@SuppressWarnings("deprecation")
	@Test
	public void testSetTime() {
		Gamer g = new Gamer("Test", 1, 1, 1.1);
		g.setTime(5.5);
		assertEquals(5.5, g.getTime(), 0.1);
	}

}
