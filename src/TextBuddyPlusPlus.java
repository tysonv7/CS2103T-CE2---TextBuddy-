/**
 * This class serves as a textUI as well as storing the main function
 * @author Khairul Rizqi Bin Mohd Shariff
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TextBuddyPlusPlus {
	
	private final static String MESSAGE_WELCOME = "Welcome to TextBuddy. %1$s is ready for use";
	private static Scanner inputScanner = new Scanner(System.in);
	
	public static void main(String[] args) throws IOException {
		Logic logicComponent = new Logic(args[0]);
		printWelcomeMessage(args);
		executeUserInputs(logicComponent);
	}
	/**
	 * Forwards to Logic inputs from user
	 * @param logicComponent	The logicComponent object that is used to 
	 * 							process commands
	 * @throws IOException		Happens if storage operations are unable to
	 * 							read/write to file
	 */
	private static void executeUserInputs(Logic logicComponent) throws IOException {
		while(true) {
			logicComponent.parseCommand(inputScanner.next(), inputScanner.nextLine());
		}
	}
	
	public static void printArrayListToScreen(ArrayList<String> outputToScreen) {
		for (int i = 0; i < outputToScreen.size();i++) {
			printTextToScreen(outputToScreen.get(i));
		}
	}
	
	public static void printTextToScreen(String outputToScreen){
		System.out.println(outputToScreen);
	}
	private static void printWelcomeMessage(String[] args) {
		System.out.println(String.format(MESSAGE_WELCOME, args[0]));
	}

}
