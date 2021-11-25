import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Survey implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private static final Scanner scanner = new Scanner(System.in);
    public Question question;
    public ArrayList<Question> questionsList = new ArrayList<>();
    public String surveyName;


    public Survey() {
    }

    /**
     * Displays Menu 2
     * User can add 6 types of questions to a survey:
     * T/F, Multiple Choice, Short Answer, Essay, Date, and Matching
     * Or they can return to Menu 1
     */
    public void surveyMenu() {
        boolean menuTwo = true;
        while (menuTwo) {
            System.out.println("Menu 3");
            System.out.println("1) Add a new T/F question");
            System.out.println("2) Add a new multiple-choice question");
            System.out.println("3) Add a new short answer question");
            System.out.println("4) Add a new essay question");
            System.out.println("5) Add a new date question");
            System.out.println("6) Add a new matching question");
            System.out.println("7) Return to previous menu");
            String check = scanner.nextLine();
            while (catchException(check)) {
                System.out.println("Please enter a number:");
                check = scanner.nextLine();
            }
            int option = Integer.parseInt(check);
            switch (option) {
                case 1:
                    question = new TF();
                    question.createQuestion();
                    questionsList.add(question);
                    break;
                case 2:
                    question = new MultipleChoice();
                    question.createQuestion();
                    questionsList.add(question);
                    break;
                case 3:
                    question = new ShortAnswer();
                    question.createQuestion();
                    questionsList.add(question);
                    break;
                case 4:
                    question = new Essay();
                    question.createQuestion();
                    questionsList.add(question);
                    break;
                case 5:
                    question = new ValidDate();
                    question.createQuestion();
                    questionsList.add(question);
                    break;
                case 6:
                    question = new Matching();
                    question.createQuestion();
                    questionsList.add(question);
                    break;
                case 7:
                    menuTwo = false;
                    break;
                default:
                    System.out.println("Please enter a valid option number from the menu.");
                    break;
            }
        }
    }

    // Displays each question in survey and allows user to fill in their response
    public void take() {
        int number = 1;
        for (Question value : questionsList) {
            System.out.print(number + ") ");
            value.display();
            value.takeResponse();
            System.out.println();
            number++;
        }
    }

    // Displays each question and response if there are any responses filled
    public void display() {
        int number = 1;
        for (Question value : questionsList) {
            System.out.print(number + ") ");
            value.display();
            System.out.println();
            number++;
        }
    }

    // Allows user to modify a question of their choice from the survey
    public void modify() {
        System.out.println("What question do you wish to modify?");
        int chosenQuestion = Integer.parseInt(scanner.nextLine()) - 1;
        questionsList.get(chosenQuestion).modify();
    }

    // Handles improper input for Menu 1 & 2 option selection
    protected boolean catchException(String input) {
        try {
            int intValue = Integer.parseInt(input);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    protected void tabulate(Survey[] surveys) {
        HashMap<Question, ArrayList<String>> listCheck = new HashMap<>();
        ArrayList<ArrayList<String>> allRes = new ArrayList<>();
        for (Survey survey : surveys) {
            for (Question question : survey.questionsList) {
                allRes.add(question.res.responseList);
                break;
            }
        }
        for (Question question : surveys[0].questionsList) {
            System.out.println(question);
            for (ArrayList<String> res : allRes) {
                listCheck.put(question, res);
                System.out.println(res);
            }
        }

    }


}
