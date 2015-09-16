import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class TextBuddyPlusPlus {
	
	private final static String MESSAGE_WELCOME = "Welcome to TextBuddy. %1$s is ready for use";
	private static Scanner inputScanner = new Scanner(System.in);
	public static void main(String[] args) throws IOException {
		Logic logicComponent = new Logic(args[0]);
		printWelcomeMessage(args);
		inputForwarding(logicComponent);
	}
	private static void inputForwarding(Logic logicComponent) throws IOException {
		while(true){
			String input = inputScanner.next();
			String variables = null;
			try{
				variables = inputScanner.nextLine();
			}catch(NoSuchElementException e){
				
			}
			logicComponent.parseCommand(input,variables);
		}
	}
	public static void arrayListPrinter(ArrayList<String> inputReturns) {
		for (int i = 0; i < inputReturns.size();i++){
			textPrinter(inputReturns.get(i));
		}
	}
	
	public static void textPrinter(String output){
		System.out.println(output);
	}
	private static void printWelcomeMessage(String[] args) {
		System.out.println(String.format(MESSAGE_WELCOME, args[0]));
	}

}
