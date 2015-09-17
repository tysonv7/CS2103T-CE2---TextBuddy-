import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class StorageTest {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final String LINE_SEPARATOR = System.getProperty("line.separator");
	private Storage testStorage;
	
	@Before
	public void setUpStreams(){
		System.setOut(new PrintStream(outContent));
	}
	
	@After
	public void cleanUpStreams() throws IOException{
		System.setOut(System.out);
		testStorage.clear();
	}
	@Before
	public void initaliseStorage() throws IOException{
		testStorage = new Storage("testStorage.txt");
	}
	
	
	@Test
	public void clearTest() throws IOException{
		testStorage.clear();
		assertEquals("all content deleted from testStorage.txt"+LINE_SEPARATOR,outContent.toString());
	}

	@Test
	public void addTest() throws IOException {
		testStorage.add(" Sherry");
		assertEquals("added to testStorage.txt: \"Sherry\""+LINE_SEPARATOR,outContent.toString());
	}
	
	@Test
	public void deleteTest() throws IOException{		
		testStorage.delete(" 1");
		assertEquals("deleted from testStorage.txt: \"Sherry\""+LINE_SEPARATOR,outContent.toString());
	}
	

	@Test 
	public void sortTest() throws IOException{
		testStorage.sort();
		assertEquals("testStorage.txt is sorted"+LINE_SEPARATOR,outContent.toString());
	}
	
	@Test
	public void searchTest(){
		
	}
}
