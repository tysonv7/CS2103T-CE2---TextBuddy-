import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Logic {
	private final static String COMMAND_ADD = "add";
	private final static String COMMAND_DISPLAY = "display";
	private final static String COMMAND_DELETE = "delete";
	private final static String COMMAND_CLEAR = "clear";
	private final static String COMMAND_EXIT = "exit";
	private static Storage storageComponent;



	public Logic(String filename) throws IOException {
		storageComponent = new Storage(filename);
	}

	public void parseCommand(String input,String variables) throws IOException {
		if (input.equals(COMMAND_ADD)){
			fileAdder(variables);
		}else if (input.equals(COMMAND_CLEAR)){
			fileClearer();
		}else if (input.equals(COMMAND_DISPLAY)){
			fileDisplayer();
		}else if (input.equals(COMMAND_EXIT)){
			systemExiter();
		}else if (input.equals(COMMAND_DELETE)){
			fileDeleter(variables);
		}
	}

	private static void systemExiter() throws IOException {
		storageComponent.exit();
	}

	private void fileDeleter(String variables) throws IOException {
		storageComponent.delete(variables);
	}

	private void fileDisplayer() throws FileNotFoundException {
		storageComponent.display();
	}

	private void fileClearer() throws IOException {
		storageComponent.clear();
	}

	private void fileAdder(String variables) throws IOException {
		storageComponent.add(variables);
	}
	
	public static void getMessage(String message){
		TextBuddyPlusPlus.textPrinter(message);
	}
	
	public static void getDisplayData(ArrayList<String> message){
		TextBuddyPlusPlus.arrayListPrinter(message);
	}

}
