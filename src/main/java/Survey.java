import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
     * Displays Menu 3
     * User can add 6 types of questions to a survey or test:
     * T/F, Multiple Choice, Short Answer, Essay, Date, and Matching
     * Or they can return to Menu 2
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
            System.out.println("Please enter your response:");
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

    /**
     * Takes in a list of filled out surveys
     * Maps all responses given in the surveys to their respective question
     * Counts frequency of each response for a given question (tabulation)
     */
    protected void tabulate(Survey[] surveys) {
        HashMap<Question, ArrayList<ResponseCorrectAnswer>> listCheck = new HashMap<>();
        for (int i = 0; i < surveys[0].questionsList.size(); i++) {
            ArrayList<ResponseCorrectAnswer> allRes = new ArrayList<>();
            for (Survey survey : surveys) {
                allRes.add(survey.questionsList.get(i).res);
            }
            listCheck.put(surveys[0].questionsList.get(i), allRes);
        }
        for (Question question : surveys[0].questionsList) {
            ArrayList<ArrayList<String>> allResponses = new ArrayList<>();
            for (ResponseCorrectAnswer x : listCheck.get(question)) {
                allResponses.add(x.responseList);
            }
            if (question instanceof TF || question instanceof ValidDate || question instanceof ShortAnswer || question instanceof Matching) {
                System.out.println();
                question.display();
                countResponses(allResponses);
            } else if (question instanceof MultipleChoice) {
                System.out.println();
                question.display();
                countMultipleResponses(allResponses);
            } else {
                System.out.println();
                question.display();
                displayAllEssay(allResponses);
            }
        }
    }

    // Outputs all responses to an essay question
    private void displayAllEssay(ArrayList<ArrayList<String>> allResponses) {
        HashMap<ArrayList<String>, Integer> answerCount = new HashMap<>();
        for (ArrayList<String> item : allResponses) {
            if (answerCount.containsKey(item)) {
                answerCount.put(item, answerCount.get(item) + 1);
            } else {
                answerCount.put(item, 1);
            }
        }
        for (Map.Entry<ArrayList<String>, Integer> entry : answerCount.entrySet()) {
            System.out.println(entry.getKey().get(0));
        }
    }

    // Outputs each response and frequency of that response
    protected void countResponses(ArrayList<ArrayList<String>> allResponses) {
        HashMap<ArrayList<String>, Integer> answerCount = new HashMap<>();
        for (ArrayList<String> item : allResponses) {
            if (answerCount.containsKey(item)) {
                answerCount.put(item, answerCount.get(item) + 1);
            } else {
                answerCount.put(item, 1);
            }
        }
        for (Map.Entry<ArrayList<String>, Integer> entry : answerCount.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    // Outputs each response to a Multiple Choice question and frequency of that response
    protected void countMultipleResponses(ArrayList<ArrayList<String>> allResponses) {
        HashMap<String, Integer> answerCount = new HashMap<>();
        for (ArrayList<String> item : allResponses) {
            for (String check : item) {
                if (answerCount.containsKey(check)) {
                    answerCount.put(check, answerCount.get(check) + 1);
                } else {
                    answerCount.put(check, 1);
                }
            }
        }
        for (Map.Entry<String, Integer> entry : answerCount.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

    }


}
