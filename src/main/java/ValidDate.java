import java.util.ArrayList;
import java.util.Scanner;

public class ValidDate extends Question implements java.io.Serializable {
    private static final long serialVersionUID = -8634676615085528642L;
    private static final Scanner scanner = new Scanner(System.in);

    @Override
    public void display() {
        System.out.println(prompt);
        System.out.println("A date should be entered in the following format: YYYY-MM-DD");

    }

    // Allows user to modify prompt and validates new prompt
    @Override
    public void modify() {
        System.out.println(prompt);
        System.out.println("Do you wish to modify the prompt?");
        if (scanner.nextLine().equalsIgnoreCase("Yes")) {
            System.out.println("Enter new prompt:");
            prompt = scanner.nextLine();
            validatePrompt();
        }
    }

    // Creates date question and validates inputted prompt
    @Override
    public void createQuestion() {
        System.out.println("Enter the prompt for your date question:");
        prompt = scanner.nextLine();
        validatePrompt();
    }

    // Takes in user response, adds to question's response list, and validates response
    @Override
    public void takeResponse() {
        responses = new ArrayList<>();
        System.out.println("Please enter your response:");
        responses.add(scanner.nextLine());
        res = new ResponseCorrectAnswer(responses);
        res.validateDate();


    }


}
