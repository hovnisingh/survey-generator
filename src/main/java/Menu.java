import java.io.File;
import java.io.FilenameFilter;
import java.util.Scanner;

public class Menu extends Survey implements java.io.Serializable {
    private static final long serialVersionUID = 4331762040471431012L;
    File f = new File("surveys");
    File f2 = new File("filledSurveys");
    FilenameFilter filter = (f, name) -> name.endsWith(".ser");
    String[] files = f.list(filter);
    private Survey loadedSurvey;
    private Survey survey;
    private Test test;
    private Test loadedTest;


    public Menu() {
    }

    public void menuOne() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Menu 1");
            System.out.println("1) Survey");
            System.out.println("2) Test");
            System.out.println("3) Quit");
            String check = scanner.nextLine();
            while (catchException(check)) {
                System.out.println("Please enter a number:");
                check = scanner.nextLine();
            }
            int option = Integer.parseInt(check);

            switch (option) {
                case 1:
                    displaySurveyMenu();
                    break;
                case 2:
                    displayTestMenu();
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please enter a valid option number from the menu.");
                    break;
            }
        }
    }

    private void displayTestMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Test Menu 2");
            System.out.println("1) Create a new Test");
            System.out.println("2) Display an existing Test without correct answers");
            System.out.println("3) Display an existing Test with correct answers");
            System.out.println("4) Load an existing Test");
            System.out.println("5) Save the current Test");
            System.out.println("6) Take the current Test");
            System.out.println("7) Modify the current Test");
            System.out.println("8) Tabulate a Test");
            System.out.println("9) Grade a Test");
            System.out.println("10) Return to the previous menu");
            String check = scanner.nextLine();
            while (catchException(check)) {
                System.out.println("Please enter a number:");
                check = scanner.nextLine();
            }
            int option = Integer.parseInt(check);
            // Calls each option function based on user input
            switch (option) {
                case 1:
                    test = new Test();
                    test.surveyMenu();
                    test.setAnswers();
                    break;
                case 2:
                    if (loadedTest == null) {
                        System.out.println("You must have a test loaded in order to display it.");
                    } else {
                        display(loadedTest);
                    }
                    break;
                case 3:
                    if (loadedTest == null) {
                        System.out.println("You must have a test loaded in order to display it with correct answers.");
                    } else {
                        displayWithAnswers(loadedTest);
                    }
                    break;
                case 4:
                    loadTest();
                    break;
                case 5:
                    save(test);
                    break;
                case 6:
                    if (loadedTest == null) {
                        System.out.println("You must have a test loaded in order to take it.");
                    } else {
                        take(loadedTest);
                    }
                    break;
                case 7:
                    if (loadedTest == null) {
                        System.out.println("You must have a test loaded in order to modify it.");
                    } else {
                        modify(loadedTest);
                    }
                    break;
                case 8:
                    break;
                case 9:
                    gradeTest();
                    break;
                case 10:
                    menuOne();
                    break;
                default:
                    System.out.println("Please enter a valid option number from the menu.");
                    break;
            }
        }
    }


    /**
     * Displays Menu 1
     * User can create a survey, display a loaded survey,
     * save the current survey, load a survey, take a loaded survey, or
     * modify a loaded survey
     */
    public void displaySurveyMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Survey Menu 2");
            System.out.println("1) Create a new Survey");
            System.out.println("2) Display an existing Survey");
            System.out.println("3) Load an existing Survey");
            System.out.println("4) Save the current Survey");
            System.out.println("5) Take the current Survey");
            System.out.println("6) Modify the current Survey");
            System.out.println("7) Tabulate a survey");
            System.out.println("8) Return to previous menu");
            String check = scanner.nextLine();
            while (catchException(check)) {
                System.out.println("Please enter a number:");
                check = scanner.nextLine();
            }
            int option = Integer.parseInt(check);
            // Calls each option function based on user input
            switch (option) {
                case 1:
                    survey = new Survey();
                    survey.surveyMenu();
                    break;
                case 2:
                    if (loadedSurvey == null) {
                        System.out.println("You must have a survey loaded in order to display it.");
                    } else {
                        display(loadedSurvey);
                    }
                    break;
                case 3:
                    loadSurvey();
                    break;
                case 4:
                    save(survey);
                    break;
                case 5:
                    if (loadedSurvey == null) {
                        System.out.println("You must have a survey loaded in order to take it.");
                    } else {
                        take(loadedSurvey);
                    }
                    break;
                case 6:
                    if (loadedSurvey == null) {
                        System.out.println("You must have a survey loaded in order to modify it.");
                    } else {
                        modify(loadedSurvey);
                    }
                    break;
                case 7:
                    tabulateSurvey();
                    break;
                case 8:
                    menuOne();
                    break;
                default:
                    System.out.println("Please enter a valid option number from the menu.");
                    break;
            }
        }
    }


    public void display(Survey survey) {
        survey.display();
    }

    public void displayWithAnswers(Test test) {
        test.displayWithAnswers();
    }


    public void loadSurvey() {
        Serialize ser = new Serialize();
        loadedSurvey = ser.deserializeSurvey();
    }

    public void loadTest() {
        Serialize ser = new Serialize();
        loadedTest = ser.deserializeTest();
    }

    public void gradeTest() {
        Serialize ser = new Serialize();
        loadedTest = ser.deserializeFilledTest();
        loadedTest.grade();
    }


    public void save(Survey survey) {
        survey.display();
        Serialize ser = new Serialize();
        ser.serialize(survey);
    }

    public void save(Test test) {
        test.display();
        Serialize ser = new Serialize();
        ser.serialize(test);
    }

    public void take(Survey survey) {
        Serialize ser = new Serialize();
        survey.take();
        ser.serializeFilledSurveys(survey);
    }

    public void take(Test test) {
        Serialize ser = new Serialize();
        test.take();
        ser.serializeFilledTests(test);
    }

    public void modify(Survey survey) {
        survey.modify();

    }

    public void modify(Test test) {
        test.modify();
    }


    public void tabulateSurvey() {
        int num = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the survey you want to tabulate. Choose from the list below.");
        for (String file : files) {
            System.out.println(file);
        }
        String nameSurvey = scanner.nextLine();
        FilenameFilter filter = (f, name) -> name.startsWith(nameSurvey);
        String[] files2 = f2.list(filter);

        Survey[] listOfSurveys = new Survey[files2.length];
        for (String file : files2) {
            System.out.println(file);
            Serialize ser = new Serialize();
            Survey survey = ser.deserializeFilledSurveys(file);
            listOfSurveys[num] = survey;
            num++;
        }
        //tabulate(listOfSurveys);

    }


}
