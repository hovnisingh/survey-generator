import java.util.ArrayList;
import java.util.Scanner;

public class Test extends Survey implements java.io.Serializable {

    private static final long serialVersionUID = -1570645570118871214L;
    private static final Scanner scanner = new Scanner(System.in);
    public ArrayList<ResponseCorrectAnswer> correctAnswers = new ArrayList<>();
    public String testName;


    public Test() {
    }

    // Displays a Test with its correct answers
    public void displayWithAnswers() {
        int number = 1;
        for (Question value : questionsList) {
            System.out.print(number + ") ");
            value.display();
            System.out.println("Correct answer(s):");
            correctAnswers.get(number - 1).display();
            System.out.println();
            number++;
        }
    }

    /**
     * Allows a user to modify a question of their choice from a Test
     * User can modify a question's prompt, choices, or the correct answers
     */
    public void modifyTest() {
        System.out.println("What question do you wish to modify?");
        int chosenQuestion = Integer.parseInt(scanner.nextLine()) - 1;
        questionsList.get(chosenQuestion).modify();
        System.out.println("Do you wish to modify the correct answer for this question?");
        if (scanner.nextLine().equals("Yes")) {
            questionsList.get(chosenQuestion).res.display();
            System.out.println("Enter new correct answer:");
            questionsList.get(chosenQuestion).takeResponse();
            correctAnswers.set(chosenQuestion, questionsList.get(chosenQuestion).res);
        }
    }

    // Allows user to set the correct answers for each question in a test
    public void setAnswers() {
        for (Question value : questionsList) {
            value.display();
            System.out.println("Set the correct answers for this question.");
            value.takeResponse();
            correctAnswers.add(value.res);
        }
    }


    /**
     * Gets value of each question based on number of total questions
     * Compares the responses for each question and adds value of question to total grade if
     * user responses for the question and correct responses for the question are the same
     * Does not grade essay questions
     */
    public void grade() {
        double grade = 0.00;

        double valueOfQuestion = 100.00 / questionsList.size();
        int numOfEssayQuestions = 0;
        for (Question question : questionsList) {
            if (!(question instanceof Essay) || (question instanceof ShortAnswer)) {
                for (ResponseCorrectAnswer correctAnswer : correctAnswers) {
                    if (correctAnswer.compare(correctAnswer, question.res)) {
                        grade += valueOfQuestion;
                    }
                }
            } else {
                numOfEssayQuestions += 1;
            }
        }
        System.out.println("You received " + grade + " on the test.");
        if (numOfEssayQuestions > 0) {
            System.out.println("The test was worth 100 points, but only " + (100 - (valueOfQuestion * numOfEssayQuestions))
                    + " of those points could be graded because there was " + numOfEssayQuestions + " essay question(s).");
        } else {
            System.out.println("The test was worth 100 points.");
        }
        System.out.println();
    }
}
