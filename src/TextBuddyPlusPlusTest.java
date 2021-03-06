import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.*;
import org.junit.Test;

public class TextBuddyPlusPlusTest {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final String LINESEPARATOR = System.getProperty("line.separator");
	
	@Before
	public void setUpStreams(){
		System.setOut(new PrintStream(outContent));
	}
	
	@After
	public void cleanUpStreams(){
		System.setOut(System.out);
	}

	@Test
	public void testArrayPrinter() {
		ArrayList<String> test = new ArrayList<String>();
		test.add("This");
		test.add("is");
		test.add("stupid");
		TextBuddyPlusPlus.printArrayListToScreen(test);
		assertEquals("This" + LINESEPARATOR + "is" + LINESEPARATOR + "stupid" + LINESEPARATOR, outContent.toString());
	}

	@Test
	public void testTextPrinter() {
		TextBuddyPlusPlus.printTextToScreen("hello");
		assertEquals("hello" + LINESEPARATOR, outContent.toString());
	}

}
