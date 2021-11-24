import java.util.ArrayList;
import java.util.Scanner;

public class Matching extends Question implements java.io.Serializable {
    private static final long serialVersionUID = -826238737863452006L;
    private static final Scanner scanner = new Scanner(System.in);
    private int numChoices;
    private ArrayList<String> leftOptions = new ArrayList<>();
    private ArrayList<String> rightOptions = new ArrayList<>();

    // Displays Matching question with left side formatted with letters and right side formatted with numbers
    @Override
    public void display() {
        System.out.println(prompt);
        int number = 1;
        for (int i = 0; i < numChoices; i++) {
            System.out.println(number + ") " + leftOptions.get(i) + "           " + choiceLetters[i] + ") " + rightOptions.get(i));
            number++;
        }

    }

    // Allows user to modify the prompt, left side choices, and right side choices
    @Override
    public void modify() {
        System.out.println(prompt);
        System.out.println("Do you wish to modify the prompt?");
        if (scanner.nextLine().equals("Yes")) {
            System.out.println("Enter new prompt:");
            prompt = scanner.nextLine();
            validatePrompt();
        }
        System.out.println("Do you wish to modify the left choices?");
        modifyChoices(leftOptions);
        System.out.println("Do you wish to modify the right choices?");
        modifyChoices(rightOptions);
    }

    /**
     * Takes in list of either left side choices or right side choices depending on user input
     * Allows user to modify selected choice and validates selected choice
     * Validates newly inputted choice
     */
    private void modifyChoices(ArrayList<String> options) {
        if (scanner.nextLine().equals("Yes")) {
            int index = 1;
            for (int i = 0; i < options.size(); i++) {
                System.out.println(index + ") " + options.get(i));
                index++;
            }
            System.out.println("Which choice do you want to modify?");
            String check = scanner.nextLine();
            int chosenChoice = validateNumber(check) - 1;
            while (chosenChoice >= numChoices) {
                System.out.println("Please select a valid choice:");
                chosenChoice = Integer.parseInt(scanner.nextLine()) - 1;
            }
            System.out.println("Enter new choice:");
            String newChoice = scanner.nextLine();
            newChoice = validateString(newChoice);
            for (int i = 0; i < options.size(); i++) {
                options.set(chosenChoice, newChoice);
            }
        }
    }

    // Creates Matching question, validates prompt and number of options
    // Sets left and right side options
    @Override
    public void createQuestion() {
        System.out.println("Enter the prompt for your Matching question:");
        prompt = scanner.nextLine();
        validatePrompt();
        System.out.println("Set number of options:");
        String check = scanner.nextLine();
        numChoices = validateNumber(check);
        setLeftOptions();
        setRightOptions();
    }

    // Creates left side options and validates each choice inputted
    private void setLeftOptions() {
        System.out.println("Set left column choices:");
        for (int i = 1; i <= numChoices; i++) {
            System.out.println("Enter option #" + i + ":");
            String check = scanner.nextLine();
            check = validateString(check);
            leftOptions.add(check);
        }

    }

    // Creates right side options and validates each choice inputted
    private void setRightOptions() {
        System.out.println("Set right column choices:");
        for (int i = 1; i <= numChoices; i++) {
            System.out.println("Enter option #" + i + ":");
            String check = scanner.nextLine();
            check = validateString(check);
            rightOptions.add(check);
        }
    }

    // Takes in multiple user responses, add to question's response list, and validates all responses inputted
    @Override
    public void takeResponse() {
        responses = new ArrayList<>();
        System.out.println("Please enter your response (Number followed by matching letter):");
        takeMultipleResponses(responses);
        res = new ResponseCorrectAnswer(responses);
        res.validateMatching(numChoices);
    }


}
