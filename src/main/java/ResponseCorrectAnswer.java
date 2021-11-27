import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class ResponseCorrectAnswer implements java.io.Serializable {
    private static final long serialVersionUID = -6641864898433385067L;
    private static final Scanner scanner = new Scanner(System.in);
    private final String[] choiceLetters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    public ArrayList<String> responseList;


    public ResponseCorrectAnswer(ArrayList<String> res) {
        responseList = res;
    }

    // Displays each response in ArrayList of responses
    public void display() {
        for (String res : responseList) {
            System.out.println(res);
        }
    }

    // Validates that T/F question response is either T or F
    public void validateTrueOrFalse() {
        String val = responseList.get(0);
        while (!val.equalsIgnoreCase("T") && !val.equalsIgnoreCase("F")) {
            System.out.println("Please enter a valid response:");
            responseList.clear();
            responseList.add(scanner.nextLine());
            val = responseList.get(0);
        }
    }

    // Validates that date question response is valid
    public void validateDate() {
        while (!getDate()) {
            System.out.println("Please enter a valid response:");
            responseList.clear();
            responseList.add(scanner.nextLine());
        }
    }

    // Validates that matching question responses is the same number as the number of options
    public void validateMatching(int num) {
        while (responseList.size() != num) {
            System.out.println("Please enter at least " + num + " responses:");
            responseList.clear();
            takeMultipleResponses();
        }
    }

    // Validates that multiple choice question responses is not greater than the number of choices
    // Validates that choices entered are the same as choices for the question
    public void validateMC(int num) {
        while (responseList.size() > num) {
            System.out.println("Please enter a valid response: ");
            responseList.clear();
            takeMultipleResponses();
        }
        ArrayList<String> arr2 = new ArrayList<>(Arrays.asList(choiceLetters).subList(0, num));
        while (!checkLetters(arr2)) {
            System.out.println("Please enter a valid response: ");
            responseList.clear();
            takeMultipleResponses();
        }
    }


    // Validates that short answer question response does not go over the word limit set
    public void validateShortAnswer(int wordLimit) {
        String answer = responseList.get(0);
        while (answer.equals("")) {
            System.out.println("Please enter a valid response:");
            responseList.clear();
            responseList.add(scanner.nextLine());
        }

        while (answer.split("\\s+").length > wordLimit) {
            System.out.println("Please enter a valid response:");
            responseList.clear();
            responseList.add(scanner.nextLine());
            answer = responseList.get(0);
        }
    }

    // Validates responses are a part of the choice letters
    private boolean checkLetters(ArrayList<String> arr2) {
        boolean found = false;
        for (String object1 : responseList) {
            for (String object2 : arr2) {
                if (object1.equalsIgnoreCase(object2)) {
                    found = true;
                    break;
                } else {
                    found = false;
                }
            }
            if (!found) {
                return false;
            }
        }
        return found;
    }

    /**
     * Takes in multiple responses again if response is not validated
     * Used for Matching, Essay, and Multiple choice question responses
     */
    private void takeMultipleResponses() {
        String check = scanner.nextLine();
        while (!check.equals("")) {
            responseList.add(check);
            check = scanner.nextLine();
        }
    }


    // Checks if date inputted is in correct format of YYYY-MM-DD
    private boolean getDate() {
        String value = responseList.get(0);
        Date date;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            date = sdf.parse(value);
            return value.equals(sdf.format(date));
        } catch (ParseException ex) {
            return false;
        }
    }

    /**
     * Takes in an ArrayList of correct responses and an ArrayList of user responses for a question
     * Returns false if size of correct responses and size of user responses are not equal
     * Check if the correct responses contains each response in the user responses, returns false if not
     * Returns true if user responses for a question is the same as the correct answers
     */
    public boolean compare(ResponseCorrectAnswer correct, ResponseCorrectAnswer user) {
        boolean check = false;
        if (correct.responseList.size() != user.responseList.size()) {
            return false;
        }
        for (int i = 0; i < user.responseList.size(); i++) {
            if (correct.responseList.get(i).contains(user.responseList.get(i))) {
                check = true;
            } else {
                return false;
            }
        }
        return check;
    }
}


