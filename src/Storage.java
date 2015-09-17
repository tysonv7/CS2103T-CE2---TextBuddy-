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
	private final static String DONE_EMPTY_COMMAND = " is empty";
	private final static String DONE_CLEAR_COMMAND = "all content deleted from ";
	private final static String DONE_DELETE_COMMAND = "deleted from %1$s: \"%2$s\"";
	private final static String DONE_ADD_COMMAND = "added to %1$s: \"%2$s\"";
	private final static String DONE_SORT_COMMAND = " is sorted";
	private static String filename;
	static FileWriter newFile;
	static PrintWriter fileWriter;
	static BufferedWriter fileWriterBuffer;
	static FileReader fileReader;
	static BufferedReader textReader;
	static ArrayList<String> textBuffer = new ArrayList<String>();
	static int lineCounter = 1;
	
	public Storage(String file) throws IOException {
		filename = file;
		fileInitialiser(file);
		textFileReader(file);
	}

	private void textFileReader(String file) throws IOException {
		fileReaderInitialiser();
		String line;
		while ((line = textReader.readLine()) != null) {
			textBuffer.add(line);
			lineCounter++;
		}
	
	}

	private static void fileInitialiser(String filename) throws IOException {
		fileWriter = new PrintWriter(new FileWriter(filename,true));
		fileWriterBuffer = new BufferedWriter(fileWriter);
	}

	public void add(String textInput) throws IOException {
		String toBePrinted = lineCounter + "." + textInput;
		textBuffer.add(toBePrinted);
		/**to add the numbering in front of the text */ 
		fileWriter.println(toBePrinted);
		fileWriter.flush();
		lineCounter++;
		/**to remove the empty space before the textInput */
		Logic.getMessage(String.format(DONE_ADD_COMMAND, filename,textInput.substring(1))); 
		
	}
	
	public void clear() throws IOException {
		fileInitialiser(filename);
		lineCounter = 1;
		textBuffer.clear();
		Logic.getMessage(DONE_CLEAR_COMMAND + filename);
	}

	public void display() throws FileNotFoundException {
		if (textBuffer.size() == 0){
			Logic.getMessage(filename + DONE_EMPTY_COMMAND);
		}else{
			Logic.getDisplayData(textBuffer);
		}
	}

	private void fileReaderInitialiser() throws IOException {
		fileReader = new FileReader(filename);
		textReader = new BufferedReader(fileReader);
	}

	public void delete(String variables) throws IOException {
		int lineNumberToBeRemoved = Integer.parseInt(variables.substring(1))-1;
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
		//textBufferChecker();
		fileInitialiser(filename);
		writeBufferToFile();
		Logic.getMessage(String.format(DONE_DELETE_COMMAND, filename, messageToBeDeleted));
	}

	@SuppressWarnings("unused")
	private void textBufferChecker() {
		for (int i = 0; i < textBuffer.size(); i++){
			System.out.println(textBuffer.get(i));
		}
		
	}

	private static void writeBufferToFile() throws FileNotFoundException {
		fileWriter = new PrintWriter(filename);
		for (int i = 0; i < textBuffer.size(); i++){
			fileWriter.println(textBuffer.get(i));
			fileWriter.flush();
		}
	}

	public static void fileCloser() throws IOException {
		fileWriter.close();
		fileWriterBuffer.close();
		fileReader.close();
		textReader.close();
	}

	public void exit() throws IOException {
		writeBufferToFile();
		fileCloser();
		System.exit(0);
	}

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
		fileInitialiser(filename);
		writeBufferToFile();
		Logic.getMessage(filename+DONE_SORT_COMMAND);
	}

	public void search(String string) {
		int searchResultsListing = 1;
		ArrayList<String> searchResults = new ArrayList<String>();
		for (int i = 0; i < textBuffer.size(); i++){
			if(textBuffer.get(i).contains(string)){
				searchResults.add(searchResultsListing+". "+textBuffer.get(i).substring(3));
				searchResultsListing++;
			}
		}
		Logic.getDisplayData(searchResults);
	}
	
}
