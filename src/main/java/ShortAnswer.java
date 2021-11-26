import java.util.ArrayList;
import java.util.Scanner;

public class ShortAnswer extends Essay implements java.io.Serializable {
    private static final long serialVersionUID = -3862041841652195643L;
    private static final Scanner scanner = new Scanner(System.in);
    private int wordLimit;

    public ShortAnswer() {

    }

    @Override
    public void display() {
        System.out.println(prompt);
        System.out.println("Word Limit: " + wordLimit);
    }

    // Creates Short Answer question, validates inputted prompt and inputted word limit
    @Override
    public void createQuestion() {
        System.out.println("Enter the prompt for your short answer question:");
        prompt = scanner.nextLine();
        validatePrompt();
        System.out.println("Enter the word limit for your short answer question:");
        String check = scanner.nextLine();
        wordLimit = validateNumber(check);
    }

    /**
     * Allows user to modify question prompt and word limit
     * Validates new prompt and new world limit inputted
     */
    @Override
    public void modify() {
        System.out.println(prompt);
        System.out.println("Do you wish to modify the prompt?");
        if (scanner.nextLine().equalsIgnoreCase("Yes")) {
            System.out.println("Enter new prompt:");
            prompt = scanner.nextLine();
            validatePrompt();
        }
        System.out.println("Do you wish to modify the word limit?");
        if (scanner.nextLine().equalsIgnoreCase("Yes")) {
            System.out.println("Enter new word limit:");
            String check = scanner.nextLine();
            wordLimit = validateNumber(check);
        }

    }

    // Takes in response, adds to question's response list, and validates response
    @Override
    public void takeResponse() {
        responses = new ArrayList<>();
        responses.add(scanner.nextLine());
        res = new ResponseCorrectAnswer(responses);
        res.validateShortAnswer(wordLimit);
    }

}
