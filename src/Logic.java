import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Logic {
	private final static String COMMAND_ADD = "add";
	private final static String COMMAND_DISPLAY = "display";
	private final static String COMMAND_DELETE = "delete";
	private final static String COMMAND_CLEAR = "clear";
	private final static String COMMAND_EXIT = "exit";
	private final static String COMMAND_INVALID = "Invalid command! Please try again!";
	private static final String COMMAND_SORT = "sort";
	private static final String COMMAND_SEARCH = "search";
	private static final String BLANK_STRING = "";
	private static Storage storageComponent;

	public Logic(String filename) throws IOException {
		storageComponent = new Storage(filename);
	}
	
	/**
	 * Acts as a parser to determine what command was given and then passes the
	 * instruction and variables needed for the storage to carry out the
	 * instruction
	 *
	 * @param command		Takes in the instruction for Storage to act upon
	 * @param variables		Takes in the variable needed for the Storage to use
	 * @throws IOException 	Happens if the storage operations are unable to read/write
	 * 						to a file
	 */
	public void parseCommand(String command,String variables) throws IOException {
		if (command.equals(COMMAND_ADD)){
			addToTextFile(variables);
		}else if (command.equals(COMMAND_CLEAR)){
			clearTextFile();
		}else if (command.equals(COMMAND_DISPLAY)){
			displayTextFile();
		}else if (command.equals(COMMAND_EXIT)){
			exitSystem();
		}else if (command.equals(COMMAND_DELETE)){
			deleteTextEntryFromFile(variables);
		}else if (command.equals(COMMAND_SORT)){
			sortTextFile();
		}else if (command.equals(COMMAND_SEARCH)){
			searchTextFile(variables);
		}else{
			getTextMessage(COMMAND_INVALID);
		}
	}

	private void searchTextFile(String variables) {
		if(variables.equals(BLANK_STRING)){
			getTextMessage(COMMAND_INVALID);
		}else{
			storageComponent.search(variables);
		}
	}

	private void sortTextFile() throws IOException {
		storageComponent.sort();
	}

	private static void exitSystem() throws IOException {
		storageComponent.exit();
	}

	private void deleteTextEntryFromFile(String variables) throws IOException {
		if(variables.equals(BLANK_STRING)){
			getTextMessage(COMMAND_INVALID);
		}else{
			storageComponent.delete(variables);
		}
	}

	private void displayTextFile() throws FileNotFoundException {
		storageComponent.display();
	}

	private void clearTextFile() throws IOException {
		storageComponent.clear();
	}

	private void addToTextFile(String variables) throws IOException {
		if(variables.equals(BLANK_STRING)){
			getTextMessage(COMMAND_INVALID);
		}else{
			storageComponent.add(variables);
		}
	}
	
	public static void getTextMessage(String message){
		TextBuddyPlusPlus.printTextToScreen(message);
	}
	
	public static void getTextMessages(ArrayList<String> message){
		TextBuddyPlusPlus.printArrayListToScreen(message);
	}

}
