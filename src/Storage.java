import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Storage {
	private final static String DONE_EMPTY_COMMAND = " is empty";
	private final static String DONE_CLEAR_COMMAND = "all content deleted from ";
	private final static String DONE_DELETE_COMMAND = "deleted from %1$s: \"%2$s\"";
	private final static String DONE_ADD_COMMAND = "added to %1$s: \"%2$s\"";
	private static String filename;
	static FileWriter newFile;
	static PrintWriter fileWriter;
	static BufferedWriter fileWriterBuffer;
	static FileReader fileReader;
	static BufferedReader textReader;
	static int lineCounter = 1;
	static ArrayList<String> textBuffer = new ArrayList<String>();
	
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
		System.out.println(String.format(DONE_ADD_COMMAND, filename,textInput.substring(1))); 
		
	}
	
	public void clear() throws IOException {
		fileInitialiser(filename);
		lineCounter = 1;
		textBuffer.clear();
		System.out.println(DONE_CLEAR_COMMAND + filename);
	}

	public ArrayList<String> display() throws FileNotFoundException {
		if (textBuffer.size() == 0){
			System.out.println(filename + DONE_EMPTY_COMMAND);
			return null;
		}else{
			return textBuffer;
		}
	}

	private void fileReaderInitialiser() throws IOException {
		fileReader = new FileReader(filename);
		textReader = new BufferedReader(fileReader);
	}

	public void delete(String variables) throws IOException {
		int lineNumberToBeRemoved = Integer.parseInt(variables.substring(1))-1;
		lineCounter = 1;
		textBuffer.remove(lineNumberToBeRemoved);
		ArrayList<String> temp = (ArrayList<String>) textBuffer.clone();
		textBuffer = new ArrayList<String>();
		for(int i = 0; i < temp.size(); i++){
			textBuffer.add(lineCounter+temp.get(i).substring(1));
			lineCounter++;
		}
		//textBufferChecker();
		fileInitialiser(filename);
		writeBufferToFile();
	}

	private void textBufferChecker() {
		for (int i = 0; i < textBuffer.size(); i++){
			System.out.println(textBuffer.get(i));
		}
		
	}

	private void writeBufferToFile() {
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
	
}
