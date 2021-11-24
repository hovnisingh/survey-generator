import java.util.ArrayList;
import java.util.Scanner;

public abstract class Question implements java.io.Serializable {
    private static final long serialVersionUID = -549557178536242744L;
    private static final Scanner scanner = new Scanner(System.in);
    protected String[] choiceLetters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    protected ResponseCorrectAnswer res;
    protected String prompt;
    protected ArrayList<String> responses;
    protected ArrayList<ResponseCorrectAnswer> userResponses = new ArrayList<>();


    // All types of questions come from this abstract class
    // All abstract functions are overridden in each type class

    public Question() {
    }

    public abstract void display();

    public abstract void modify();

    public abstract void createQuestion();

    // Checks if input is 0
    private boolean validateNum(int num) {
        if (num == 0) {
            return true;
        }
        return false;
    }

    public abstract void takeResponse();


    // Checks if number can be parsed as an integer - handles improper input for all types of questions
    private boolean catchException(String input) {
        try {
            int intValue = Integer.parseInt(input);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    /**
     * Validates that input is a valid number
     * Uses validateNum to handle '0' inputs
     * Returns validated parsed integer
     */
    protected int validateNumber(String check) {
        while (catchException(check)) {
            System.out.println("Please enter a number.");
            check = scanner.nextLine();
        }
        int numChoices = Integer.parseInt(check);
        while (validateNum(numChoices)) {
            System.out.println("Please enter a valid number:");
            numChoices = Integer.parseInt(scanner.nextLine());
        }
        return numChoices;
    }

    /**
     * Checks if string inputted is not blank
     * Returns validated string
     */
    protected String validateString(String check) {
        while (check.equals("")) {
            System.out.println("Please enter a valid choice:");
            check = scanner.nextLine();
        }
        return check;
    }

    // Validates that prompt is not empty or a number
    protected void validatePrompt() {
        while (prompt.equals("") || !catchException(prompt)) {
            System.out.println("Please enter a valid prompt:");
            prompt = scanner.nextLine();
        }
    }

    /**
     * Allows multiple responses on different lines to be taken as input
     * Adds each response to the question's response list
     * Used for Multiple Choice, Matching, and Essay questions
     */
    protected void takeMultipleResponses(ArrayList<String> responses) {
        System.out.println("Enter twice when you are done entering in your responses.");
        String check = scanner.nextLine();
        while (!check.equals("")) {
            responses.add(check);
            check = scanner.nextLine();
        }
    }

}
