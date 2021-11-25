import java.util.ArrayList;

public class Test extends Survey implements java.io.Serializable {

    private static final long serialVersionUID = -1570645570118871214L;
    public ArrayList<ResponseCorrectAnswer> correctAnswers = new ArrayList<>();
    public String testName;


    public Test() {
    }

    public void displayWithAnswers() {
        int number = 1;
        for (Question value : questionsList) {
            System.out.print(number + ") ");
            value.display();
            System.out.println("Correct answer:" + correctAnswers.get(number - 1).responseList);
            System.out.println();
            number++;
        }
    }


    public void setAnswers() {
        for (Question value : questionsList) {
            value.display();
            System.out.println("Set the correct answers for this question.");
            value.takeResponse();
            correctAnswers.add(value.res);
        }
    }


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
