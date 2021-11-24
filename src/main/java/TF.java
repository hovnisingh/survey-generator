import java.util.ArrayList;
import java.util.Scanner;

public class TF extends MultipleChoice implements java.io.Serializable {


    private static final long serialVersionUID = 3633136777883309601L;
    private static final Scanner scanner = new Scanner(System.in);

    public TF() {
    }

    // Create T/F question and validates inputted prompt
    @Override
    public void createQuestion() {
        System.out.println("Enter the prompt for your True/False question:");
        prompt = scanner.nextLine();
        validatePrompt();
    }


    @Override
    public void display() {
        System.out.println(prompt);
        System.out.println("T/F");

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

    // Takes in user response, adds to question's response list, and validates response
    @Override
    public void takeResponse() {
        responses = new ArrayList<>();
        System.out.println("Please enter your response:");
        responses.add(scanner.nextLine());
        res = new ResponseCorrectAnswer(responses);
        res.validateTrueOrFalse();

    }
}
