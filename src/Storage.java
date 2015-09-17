import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Storage {
	private static final String INVALID_INDEX = "Invalid index";
	private final static String DONE_EMPTY_COMMAND = " is empty";
	private final static String DONE_CLEAR_COMMAND = "all content deleted from ";
	private final static String DONE_DELETE_COMMAND = "deleted from %1$s: \"%2$s\"";
	private final static String DONE_ADD_COMMAND = "added to %1$s: \"%2$s\"";
	private final static String DONE_SORT_COMMAND = " is sorted";
	private static final String KEYWORD_NOT_FOUND = "keyword: \"%1$s\" is not found";
	private static String filename;
	private static PrintWriter fileWriter;
	private static BufferedWriter fileWriterBuffer;
	private static FileReader fileReader;
	private static BufferedReader textReader;
	private static ArrayList<String> textBuffer = new ArrayList<String>();
	private static int lineCounter = 1;
	
	public Storage(String file) throws IOException {
		filename = file;
		initaliseFile(file);
		readTextFile(file);
	}
	
	/**
	 * Reads in the text file line by line and adds them into the textBuffer for
	 * processing
	 *
	 * @param file    		Name of text file
	 * @throws IOException	Happen if function unable to read from file
	 */
	private void readTextFile(String file) throws IOException {
		initaliseFileReader();
		String line;
		while ((line = textReader.readLine()) != null) {
			textBuffer.add(line);
			lineCounter++;
		}
	}

	/**
	 * Initializes the PrintWriter function. Will check for the file. If have,
	 * will use the text file, otherwise create a new file
	 *
	 * @param filename    	Name of text file
	 * @throws IOException 	Happens if function unable to create file
	 */
	private static void initaliseFile(String filename) throws IOException {
		fileWriter = new PrintWriter(new FileWriter(filename,true));
		fileWriterBuffer = new BufferedWriter(fileWriter);
	}

	/**
	 * Add message input into textBuffer and directly to the text file
	 *
	 * @param textInput    	Text message to be added to file
	 * @throws IOException	Happens if function unable to write to file
	 */
	public void add(String textInput) throws IOException {
		String messageToBePrinted = lineCounter + "." + textInput;
		textBuffer.add(messageToBePrinted);
		/**to add the numbering in front of the text */ 
		fileWriter.println(messageToBePrinted);
		fileWriter.flush();
		lineCounter++;
		/**to remove the empty space before the textInput */
		Logic.getTextMessage(String.format(DONE_ADD_COMMAND, filename,textInput.substring(1))); 
	}
	
	/**
	 * Delete all texts in the text file. 
	 *
	 * @throws IOException	Happens if unable to read/write to file
	 */
	public void clear() throws IOException {
		initaliseFile(filename);
		lineCounter = 1;
		textBuffer.clear();
		Logic.getTextMessage(DONE_CLEAR_COMMAND + filename);
	}
	
	/**
	 * Displays all the texts from the text file to screen
	 *
	 * @throws FileNotFoundException if unable to find the file
	 */
	public void display() throws FileNotFoundException {
		if (textBuffer.size() == 0){
			Logic.getTextMessage(filename + DONE_EMPTY_COMMAND);
		}else{
			Logic.getTextMessages(textBuffer);
		}
	}

	private void initaliseFileReader() throws IOException {
		fileReader = new FileReader(filename);
		textReader = new BufferedReader(fileReader);
	}
	
	/**
	 * Deletes the line requested to be deleted from text file
	 *
	 * @param variables    	Takes in the line number to be cleared from the text file
	 * @throws IOException	Happens if unable to write to file
	 */
	public void delete(String variables) throws IOException {
		int lineNumberToBeRemoved = Integer.parseInt(variables.substring(1))-1;
		if (lineNumberToBeRemoved < 0){
			Logic.getTextMessage(INVALID_INDEX);
		}else{
			lineCounter = 1;
			String messageToBeDeleted = textBuffer.get(lineNumberToBeRemoved).substring(3);
			textBuffer.remove(lineNumberToBeRemoved);
			@SuppressWarnings("unchecked")
			ArrayList<String> temp = (ArrayList<String>) textBuffer.clone();
			textBuffer = new ArrayList<String>();
			for(int i = 0; i < temp.size(); i++){
				textBuffer.add(lineCounter+temp.get(i).substring(1));
				lineCounter++;
			}
			Logic.getTextMessage(String.format(DONE_DELETE_COMMAND, filename, messageToBeDeleted));
		}
		initaliseFile(filename);
		writeTextBufferToFile();
	}
	
	/**
	 * Used to check for contents in textBuffer. Only for debugging purposes
	 */
	@SuppressWarnings("unused")
	private void checkTextBuffer() {
		for (int i = 0; i < textBuffer.size(); i++){
			System.out.println(textBuffer.get(i));
		}
	}
	
	/**
	 * Writes to text file from the textBuffer
	 *
	 * @throws FileNotFoundException Happens if unable to write to file. 
	 */
	private static void writeTextBufferToFile() throws FileNotFoundException {
		fileWriter = new PrintWriter(filename);
		for (int i = 0; i < textBuffer.size(); i++){
			fileWriter.println(textBuffer.get(i));
			fileWriter.flush();
		}
	}
	
	/**
	 * Closes all the streams leading to the text file
	 *
	 * @throws IOException	Happens if unable to close any streams to the text file
	 */
	public static void closeFile() throws IOException {
		fileWriter.close();
		fileWriterBuffer.close();
		fileReader.close();
		textReader.close();
	}
	
	/**
	 * Writes any changes done in the textBuffer into the text file and closes all streams
	 *
	 * @throws IOException	Happens if unable to write to file
	 */
	public void exit() throws IOException {
		writeTextBufferToFile();
		closeFile();
		System.exit(0);
	}
	
	/**
	 * Sorts all the content in the textBuffer by alphabetical order. 
	 * It is also case insensitive which further ensures alphabetical order
	 * Will write to file after sorting all the text inputs both in file and
	 * textBuffer
	 *
	 * @throws IOException	Happens if unable to write to file
	 */
	public void sort() throws IOException {
		ArrayList<String> temp = new ArrayList<String>();
		for (int i = 0; i < textBuffer.size(); i++){
			temp.add(textBuffer.get(i).substring(3));
		}
		textBuffer = new ArrayList<String>();
		Collections.sort(temp, String.CASE_INSENSITIVE_ORDER);
		lineCounter = 1;
		for (int i = 0; i < temp.size(); i++){
			textBuffer.add(lineCounter+". "+temp.get(i));
			lineCounter++;
		}
		initaliseFile(filename);
		writeTextBufferToFile();
		Logic.getTextMessage(filename+DONE_SORT_COMMAND);
	}
	
	/**
	 * Searches all entries in the textBuffer and returns all the entries that
	 * contains the keyword
	 */
	public void search(String keyword) {
		int searchResultsListing = 1;
		boolean isFound = false;
		ArrayList<String> searchResults = new ArrayList<String>();
		for (int i = 0; i < textBuffer.size(); i++){
			if(textBuffer.get(i).toLowerCase().contains(keyword.toLowerCase())){
				searchResults.add(searchResultsListing+". "+textBuffer.get(i).substring(3));
				searchResultsListing++;
				isFound = true;
			}
		}
		if(isFound){
			Logic.getTextMessages(searchResults);
		}else{
			/**to remove the empty space in keyword */
			Logic.getTextMessage(String.format(KEYWORD_NOT_FOUND,keyword.substring(1)));
		}
	}
}
