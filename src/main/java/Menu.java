import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Scanner;

public class Menu extends Survey implements java.io.Serializable {
    private static final long serialVersionUID = 4331762040471431012L;
    private final File f3 = new File("tests");
    private final File f4 = new File("filledTests");
    private final FilenameFilter filter3 = (f3, name) -> name.endsWith(".ser");
    private final String[] files2 = f3.list(filter3);
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

    /**
     * Displays Menu 1
     * User can go to the Survey Menu, Test Menu,
     * or exit the program
     */
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

    /**
     * Displays Test Menu 2
     * User can create a test, display a loaded test with or without answers,
     * save the current test, load a test, take a loaded test, modify a test,
     * tabulate a test, or grade a test
     */

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
                        loadedTest.modifyTest();
                    }
                    break;
                case 8:
                    tabulateTest();
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
     * Displays Survey Menu 2
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
                        loadedSurvey.modify();
                        survey = loadedSurvey;
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

    // Asks user which test they want graded and loads a filled out test to grade
    public void gradeTest() {
        Serialize ser = new Serialize();
        loadedTest = ser.deserializeFilledTest();
        if (loadedTest != null) {
            loadedTest.grade();
        }
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

    /**
     * Tabulates a Test
     * Gets all filled out tests based on which test user wants to tabulate
     * Calls tabulate() if there are responses for a specific test
     */
    public void tabulateTest() {
        int num = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the test you want to tabulate. Choose from the list below.");
        for (String file : files2) {
            System.out.println(file);
        }
        String nameTest = scanner.nextLine();
        FilenameFilter filter = (f, name) -> name.startsWith(nameTest);
        String[] files3 = f4.list(filter);
        if ((files3.length != 0) && (Arrays.asList(files2).contains(nameTest + ".ser"))) {
            Test[] listOfTests = new Test[files3.length];
            for (String file : files3) {
                Serialize ser = new Serialize();
                Test test = ser.deserializeFilledTestsForTabulation(file);
                listOfTests[num] = test;
                num++;
            }
            tabulate(listOfTests);
        } else if ((Arrays.asList(files2).contains(nameTest + ".ser")) && (files3.length == 0)) {
            System.out.println("There are no responses for this survey.");
        } else {
            System.out.println("File not found.");
        }


    }


    /**
     * Tabulates a Survey
     * Gets all filled out surveys based on which survey user wants to tabulate
     * Calls tabulate() if there are responses for a specific survey
     */
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
        if ((files2.length != 0) && (Arrays.asList(files).contains(nameSurvey + ".ser"))) {
            Survey[] listOfSurveys = new Survey[files2.length];
            for (String file : files2) {
                Serialize ser = new Serialize();
                Survey survey = ser.deserializeFilledSurveys(file);
                listOfSurveys[num] = survey;
                num++;
            }
            tabulate(listOfSurveys);
        } else if ((Arrays.asList(files).contains(nameSurvey + ".ser")) && (files2.length == 0)) {
            System.out.println("There are no responses for this survey.");
        } else {
            System.out.println("File not found.");
        }


    }


}
