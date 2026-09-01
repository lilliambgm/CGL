package main.java;

import java.util.Scanner;

public class Helper {
    private static Scanner input = new Scanner(System.in);

    //<editor-fold desc="prompters">
    /**
     * Method to ask a question in the console and return a user input as a {@code String}.
     * @param s The question to be asked.
     * @return User input from command line as {@code String}.
     * @author Lilliam
     */
    public static String prompter(String s){
        System.out.println(s);
        return input.nextLine();
    }

    /**
     * Method to ask a question in the console and return a user input as {@code integer}.
     * All it really does right now, is simply parse the result from {@link prompter} to an {@code int}.
     * @param s The question to be asked.
     * @return User input from command line Parsed into {@code int}.
     * @author Lilliam
     */
    public static int intPrompter(String s){
        return Integer.parseInt(prompter(s));
    }

    /**
     * Prompts yes or no (boolean) question.
     * @param question The question to be asked.
     * @return {@code true} if answer is a variation of "yes",
     * {@code false} if answer is a variation of "no".
     * @author Lilliam
     */
    public static boolean boolPrompter(String question) {
        System.out.println(question);
        while (true) {
            String answer = input.nextLine();
            if (answer.equalsIgnoreCase("yes") ||
                answer.equalsIgnoreCase("y")) {
                return true;
            } else if (answer.equalsIgnoreCase("no") ||
                       answer.equalsIgnoreCase("n")) {
                return false;
            } else {
                System.out.println("Invalid input, please type \"yes\" (y) or \"no\" (n)");
            }
        }
    }
    //</editor-fold>

//    public class dumpmethods
}
