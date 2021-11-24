import java.util.ArrayList;
import java.util.Scanner;

public class Essay extends Question implements java.io.Serializable {
    private static final long serialVersionUID = 2580730349749648109L;
    private static final Scanner scanner = new Scanner(System.in);

    @Override
    public void display() {
        System.out.println(prompt);
    }

    // Allows user to modify the question's prompt and validates new prompt
    @Override
    public void modify() {
        System.out.println(prompt);
        System.out.println("Do you wish to modify the prompt?");
        if (scanner.nextLine().equals("Yes")) {
            System.out.println("Enter new prompt:");
            prompt = scanner.nextLine();
            validatePrompt();
        }

    }

    // Creates Essay question about validates prompt inputted
    @Override
    public void createQuestion() {
        System.out.println("Enter the prompt for your essay question:");
        prompt = scanner.nextLine();
        validatePrompt();
    }

    // Takes in multiple user responses (paragraphs), adds responses to question's response list, and validates each response
    @Override
    public void takeResponse() {
        responses = new ArrayList<>();
        System.out.println("Please enter your response:");
        takeMultipleResponses(responses);
        res = new ResponseCorrectAnswer(responses);

    }


}
