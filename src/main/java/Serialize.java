import java.io.*;
import java.util.Scanner;


public class Serialize implements java.io.Serializable {
    private static final long serialVersionUID = 554997690247380776L;
    private static final Scanner scanner = new Scanner(System.in);
    private final File f = new File("surveys");
    private final File f2 = new File("filledSurveys");
    private final File f3 = new File("tests");
    private final File f4 = new File("filledTests");
    private final FilenameFilter filter = (f, name) -> name.endsWith(".ser");
    private final FilenameFilter filter2 = (f2, name) -> name.endsWith(".ser");
    private final FilenameFilter filter3 = (f3, name) -> name.endsWith(".ser");
    private final FilenameFilter filter4 = (f4, name) -> name.endsWith(".ser");
    private final String[] files = f.list(filter);
    private final String[] files2 = f2.list(filter2);
    private final String[] files3 = f3.list(filter3);
    private final String[] files4 = f4.list(filter4);


    /**
     * Takes in a Survey and saves object to .ser file
     * Validates name of survey is not empty
     */
    public void serialize(Survey survey) {
        System.out.println("Enter a name for your survey:");
        survey.surveyName = scanner.nextLine();
        while (checkName(survey.surveyName, files)) {
            System.out.println("Please enter a survey name that has not been created already:");
            survey.surveyName = scanner.nextLine();
        }
        while (survey.surveyName.equals("")) {
            System.out.println("Please enter a valid name for your survey:");
            survey.surveyName = scanner.nextLine();
        }
        try (FileOutputStream fos = new FileOutputStream("surveys" + File.separatorChar + survey.surveyName + ".ser");
             ObjectOutputStream oos = new ObjectOutputStream(fos);) {
            oos.writeObject(survey);
        } catch (IOException ioe) {
            System.out.println("There was a problem in saving your survey.");
            ioe.printStackTrace();
        }
    }

    public void serialize(Test test) {
        System.out.println("Enter a name for your test:");
        test.testName = scanner.nextLine();
        while (checkName(test.testName, files3)) {
            System.out.println("Please enter a test name that has not been created already:");
            test.testName = scanner.nextLine();
        }
        while (test.testName.equals("")) {
            System.out.println("Please enter a valid name for your test:");
            test.testName = scanner.nextLine();
        }
        try (FileOutputStream fos = new FileOutputStream("tests" + File.separatorChar + test.testName + ".ser");
             ObjectOutputStream oos = new ObjectOutputStream(fos);) {
            oos.writeObject(test);
        } catch (IOException ioe) {
            System.out.println("There was a problem in saving your test.");
            ioe.printStackTrace();
        }
    }

    // Takes in a Filled Survey and saves survey to .ser file in filledSurveys directory
    public void serializeFilledSurveys(Survey survey) {
        System.out.println("Enter the number of times this survey has been taken:");
        String num = scanner.nextLine();
        while (num.equals("") || num.equals("0")) {
            System.out.println("Please enter a valid number:");
            num = scanner.nextLine();
        }
        String name = survey.surveyName;
        while (responseNumber(name, num, files2)) {
            System.out.println("Please enter a different number:");
            num = scanner.nextLine();
        }
        try (FileOutputStream fos = new FileOutputStream("filledSurveys" + File.separatorChar + survey.surveyName + "Response" + num + ".ser");
             ObjectOutputStream oos = new ObjectOutputStream(fos);) {
            oos.writeObject(survey);
        } catch (IOException ioe) {
            System.out.println("There was a problem in saving your survey.");
            ioe.printStackTrace();
        }
    }

    public void serializeFilledTests(Test test) {
        System.out.println("Enter the number of times this test has been taken:");
        String num = scanner.nextLine();
        while (num.equals("") || num.equals("0")) {
            System.out.println("Please enter a valid number:");
            num = scanner.nextLine();
        }
        String name = test.testName;
        while (responseNumber(name, num, files4)) {
            System.out.println("Please enter a different number:");
            num = scanner.nextLine();
        }
        try (FileOutputStream fos = new FileOutputStream("filledTests" + File.separatorChar + test.testName + "Response" + num + ".ser");
             ObjectOutputStream oos = new ObjectOutputStream(fos);) {
            oos.writeObject(test);
        } catch (IOException ioe) {
            System.out.println("There was a problem in saving your test.");
            ioe.printStackTrace();
        }
    }

    // Checks if name for new survey has already been used
    private boolean checkName(String name, String[] files) {
        for (String x : files) {
            if (x.equals(name + ".ser")) {
                return true;
            }
        }
        return false;
    }

    // Checks if number for response file has already been used
    private boolean responseNumber(String name, String num, String[] files) {
        for (String x : files) {
            if (x.equals(name + "ResponseCorrectAnswer" + num + ".ser")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Loads .ser file to Survey object
     * Outputs list of all .ser files in directory
     * Checks if file inputted is in directory
     * Returns Survey loaded
     */

    public void deserialize(String path) {

    }

    public Survey deserializeSurvey() {
        Survey survey = null;
        int number = 1;
        System.out.println("Please select a file to load:");
        if (files.length == 0) {
            System.out.println("No survey files found in current directory.");
        } else {
            for (String file : files) {
                System.out.println(number + ") " + file);
                number++;
            }
            System.out.println("Enter the name of the survey you want to load:");
            String surveyName = scanner.nextLine();
            try (FileInputStream fis = new FileInputStream("./surveys/" + surveyName + ".ser");
                 ObjectInputStream ois = new ObjectInputStream(fis);) {
                survey = (Survey) ois.readObject();
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            } catch (IOException ioe) {
                System.out.println("Error reading survey");
                ioe.printStackTrace();
            } catch (ClassNotFoundException notFound) {
                System.out.println("Error loading survey");
                notFound.printStackTrace();
            }
        }
        return survey;
    }

    public Test deserializeTest() {
        Test test = null;
        int number = 1;
        System.out.println("Please select a file to load:");
        if (files3.length == 0) {
            System.out.println("No test files found in current directory.");
        } else {
            for (String file : files3) {
                System.out.println(number + ") " + file);
                number++;
            }
            System.out.println("Enter the name of the test you want to load:");
            String surveyName = scanner.nextLine();
            try (FileInputStream fis = new FileInputStream("./tests/" + surveyName + ".ser");
                 ObjectInputStream ois = new ObjectInputStream(fis);) {
                test = (Test) ois.readObject();
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            } catch (IOException ioe) {
                System.out.println("Error reading test");
                ioe.printStackTrace();
            } catch (ClassNotFoundException notFound) {
                System.out.println("Error loading test");
                notFound.printStackTrace();
            }
        }
        return test;
    }

    public Test deserializeFilledTest() {
        Test test = null;
        int number = 1;
        System.out.println("Please select a file to load:");
        if (files4.length == 0) {
            System.out.println("No test files found in current directory.");
        } else {
            for (String file : files4) {
                System.out.println(number + ") " + file);
                number++;
            }
            System.out.println("Enter the name of the test you want to load:");
            String surveyName = scanner.nextLine();
            try (FileInputStream fis = new FileInputStream("./filledTests/" + surveyName + ".ser");
                 ObjectInputStream ois = new ObjectInputStream(fis);) {
                test = (Test) ois.readObject();
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            } catch (IOException ioe) {
                System.out.println("Error reading test");
                ioe.printStackTrace();
            } catch (ClassNotFoundException notFound) {
                System.out.println("Error loading test");
                notFound.printStackTrace();
            }
        }
        return test;
    }

    public Survey deserializeFilledSurveys() {
        Survey survey = null;
        int number = 1;
        System.out.println("Please select a file to load:");
        if (files2.length == 0) {
            System.out.println("No survey files found in current directory.");
        } else {
            for (String file : files2) {
                System.out.println(number + ") " + file);
                number++;
            }
            System.out.println("Enter the name of the survey you want to load:");
            String surveyName = scanner.nextLine();
            try (FileInputStream fis = new FileInputStream("./filledSurveys/" + surveyName + ".ser");
                 ObjectInputStream ois = new ObjectInputStream(fis);) {
                survey = (Survey) ois.readObject();
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            } catch (IOException ioe) {
                System.out.println("Error reading test");
                ioe.printStackTrace();
            } catch (ClassNotFoundException notFound) {
                System.out.println("Error loading test");
                notFound.printStackTrace();
            }
        }
        return survey;
    }


}
