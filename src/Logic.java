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

	public ArrayList<String> command(String input,String variables) throws IOException {
		if (input.equals(COMMAND_ADD)){
			fileAdder(variables);
		}else if (input.equals(COMMAND_CLEAR)){
			fileClearer();
		}else if (input.equals(COMMAND_DISPLAY)){
			return fileDisplayer();
		}else if (input.equals(COMMAND_EXIT)){
			systemExiter();
		}else if (input.equals(COMMAND_DELETE)){
			fileDeleter(variables);
		}
		return null;
		
	}

	private void systemExiter() throws IOException {
		storageComponent.exit();
	}

	private void fileDeleter(String variables) throws IOException {
		storageComponent.delete(variables);
	}

	private ArrayList<String> fileDisplayer() throws FileNotFoundException {
		ArrayList<String> messagesToBeDisplayed = storageComponent.display();
		return messagesToBeDisplayed;
	}

	private void fileClearer() throws IOException {
		storageComponent.clear();
	}

	private void fileAdder(String variables) throws IOException {
		storageComponent.add(variables);
	}
	


}
