import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.*;
import org.junit.Test;

public class TextBuddyPlusPlusTest {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	@Before
	public void setUpStreams(){
		System.setOut(new PrintStream(outContent));
	}
	
	@After
	public void cleanUpStreams(){
		System.setOut(System.out);
	}

	@Test
	public void testMain() {
//		fail("Not yet implemented");
	}

	@Test
	public void testArrayPrinter() {
//		ArrayList<String> test = new ArrayList<String>();
//		test.add("This");
//		test.add("is");
//		test.add("stupid");
//		TextBuddyPlusPlus.arrayListPrinter(test);
//		assertEquals("This\n"+"is\n"+"stupid"+ System.getProperty("line.separator"),outContent.toString());
	}

	@Test
	public void testTextPrinter() {
		TextBuddyPlusPlus.textPrinter("hello");
		System.out.println("hello");
		System.out.println(outContent.toString());
		assertEquals("hello"+ System.getProperty("line.separator") ,outContent.toString());
	}

}
