import java.util.ArrayList;
import java.util.Scanner;

public class MultipleChoice extends Question {
    private static final long serialVersionUID = -6509796327415449477L;
    private static final Scanner scanner = new Scanner(System.in);
    protected ArrayList<String> choices = new ArrayList<>();
    private int numChoices;


    public MultipleChoice() {
    }

    @Override
    public void display() {
        int x = 0;
        System.out.println(prompt);
        for (String choice : choices) {
            System.out.println(choiceLetters[x] + ") " + choice);
            x++;
        }
    }

    /**
     * Allows user to modify prompt then validates new prompt
     * Allows user to modify a selected choice and validates selected choice
     * Validates newly inputted choice
     */
    @Override
    public void modify() {
        System.out.println(prompt);
        System.out.println("Do you wish to modify the prompt?");
        if (scanner.nextLine().equals("Yes")) {
            System.out.println("Enter new prompt:");
            prompt = scanner.nextLine();
            validatePrompt();
        }
        System.out.println("Do you wish to modify choices?");
        if (scanner.nextLine().equals("Yes")) {
            System.out.println("Which choice do you want to modify?");
            int x = 0;
            for (int i = 0; i < choices.size(); i++) {
                System.out.println(choiceLetters[x] + ") " + choices.get(i));
                x++;
            }
            String check = scanner.nextLine();
            int chosenChoice = validateNumber(check) - 1;
            while (chosenChoice >= numChoices) {
                System.out.println("Please select a valid choice:");
                chosenChoice = Integer.parseInt(scanner.nextLine()) - 1;
            }
            System.out.println("Enter new choice:");
            check = scanner.nextLine();
            check = validateString(check);
            choices.set(chosenChoice, check);
        }
    }


    // Creates Multiple Choice question and validates prompt, number of choices, and each choice inputted
    @Override
    public void createQuestion() {
        System.out.println("Enter the prompt for your multiple-choice question:");
        prompt = scanner.nextLine();
        validatePrompt();
        System.out.println("Enter the number of choices for your multiple-choice question:");
        String check = scanner.nextLine();
        numChoices = validateNumber(check);
        for (int i = 1; i <= numChoices; i++) {
            System.out.println("Enter choice #" + i + ":");
            check = scanner.nextLine();
            check = validateString(check);
            choices.add(check);
        }
    }

    // Takes in multiple user responses, adds to question's response list, and validates each response
    @Override
    public void takeResponse() {
        responses = new ArrayList<>();
        System.out.println("Please enter your response:");
        takeMultipleResponses(responses);
        res = new ResponseCorrectAnswer(responses);
        res.validateMC(numChoices);
    }


}
