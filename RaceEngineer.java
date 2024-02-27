import java.io.BufferedReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RaceEngineer {
    private String driverName;
    private String targetDriver;
    private FileReader fileReader;
    private String queriesFile = "queries.txt";


    public RaceEngineer() {
        this.fileReader = new FileReader();
        this.fileReader.setDriversList();
        this.driverName = this.fileReader.getUsername();
    }
    
    public String radio(String question) {
        String query = processQuery(question.toLowerCase());
        if (query.equals("INVALID QUERY")) {
            return "INVALID QUERY";
        }
        String data = retrieveData(query.toLowerCase());
        String response = getResponseData(query, data);

        return response;
    }

    public String processQuery(String query) {
        if (query.contains("time to") && query.contains("in front")) {
            String driverInFront = fileReader.getDriver(fileReader.getCarPosition()-1);
            return "user.deltaTo." + driverInFront;
        } else if (query.contains("time to") && query.contains("behind")) {
            String driverBehind = fileReader.getDriver(fileReader.getCarPosition()+1);
            return "user.deltaTo." + driverBehind;
        } else if (query.contains("time to")) {
            String driverName = findDriverNameInSentence(query);
            if (driverName != null) {
                return "user.deltaTo." + driverName;
            } else {
                return "INVALID QUERY";
            }
        } else if (query.contains("position")) {
            //@TODO fix this so it follows new structure
            // This condition is that the user asks about their current position
            if (query.contains("current") || query.contains("my")) {
                return "user.carPosition";
            // This condition is that the user asks about another drivers position
            // If the user asks an invalid name it will return the user
            } else {
                String returnQuery = "user.carPosition";
                String driverName = findDriverNameInSentence(query);
                if (driverName != null) {
                    return returnQuery + "." + driverName;
                } else {
                    return returnQuery;
                }
            }
        } else if (query.contains("lap")) {
            if (query.contains("fastest")) {
                if (query.contains("differnce") || query.contains("differential")) {
                    return "user.previousLap.fastestLap";
                } else {
                    return "fastestLap";
                }
            } else if (query.contains("previous")) {
                if (query.contains("my")) {
                    return "user.previousLap";
                } else if (query.contains("in front") || query.contains("ahead")) {
                    String driverAhead = fileReader.getDriver(fileReader.getCarPosition()-1);
                    return driverAhead + ".previousLap";
                } else if (query.contains("behind")) {
                    String driverBehind = fileReader.getDriver(fileReader.getCarPosition()+1);
                    return driverBehind + ".previousLap";
                } else {
                    String driverName = findDriverNameInSentence(query);
                    if (driverName != null) {
                        return driverName + ".previousLap";
                    } else {
                        return "user.previousLap";
                    }
                }
            }  else if (query.contains("pit window")) {
                return "user.pitWindow";
            }
        } else if (query.contains("tyre")) {
            if (query.contains("wear")) {
                return "user.tyreWear";
            } else if (query.contains("age")) {
                return "user.tyreAge";
            } else if (query.contains("expectancy") || query.contains("lap left")) {
                return "user.tyreExpectancy";
            } else if (query.contains("swap") || query.toLowerCase().contains("next")) {
                return "user.nextTyre";
            } else {
                return "user.tyreWear"; // cheap coding
            }
        } else if (query.contains("who am i racing")) {
            return "user.racing"; 
        }

        return null;
    }

    public String retrieveData(String request) {
        String[] dataWanted = request.split("\\.");
        for (String string : dataWanted) {
            System.out.println(string);
        }
        if (dataWanted[0].equals("user")) {
            if (dataWanted[1].equals("deltaTo")) {
                // The car in front
                if (fileReader.getCarPosition(dataWanted[2]) == fileReader.getCarPosition()-1) {
                    return Float.toString(fileReader.getDeltaToCarInFront());
                // Other car
                } else {
                    return Float.toString(fileReader.getDeltaToDriver(dataWanted[2]));
                } 
            } else if (dataWanted[1].equals("previousLap")) { // IF NOT FASTEST LAP/PREVIOUS LAP HAS BEEN SET RETURN ERROR
                // CREATE A CHECK TO SEE IF THE DRIVER HAS DRIVEN A LAP
                if (dataWanted[2] != null & dataWanted[2].equals("fastestLap")) {
                    return Float.toString(fileReader.getPreviousLapFastestLapDifference());
                } else {
                    return Float.toString(fileReader.getPreviousLap()); // IF NOT FASTEST LAP/PREVIOUS LAP HAS BEEN SET RETURN ERROR
                }
            } else if (dataWanted[1].equals("carPosition")) {
                if (dataWanted.length == 3) {
                    return Integer.toString(fileReader.getCarPosition(dataWanted[2]));
                } else {
                    return Integer.toString(fileReader.getCarPosition());
                }
            } else if (dataWanted[1].equals("pitWindow")) {
                return Float.toString(fileReader.getPitWindow()); //IN ORDER TO ADD THIS SESSION DATA PACKET NEEDS TO BE CREATED
            } else if (dataWanted[1].toLowerCase().contains("tyre")) {
                if (dataWanted[1].equals("tyreWear")) {
                    return Float.toString(fileReader.getTyreWear());
                } else if (dataWanted[1].equals("tyreAge")) { //Car status data needed
                    return Float.toString(fileReader.getTyreAge());
                } else if (dataWanted[1].equals("tyreExpectancy")) {
                    return Float.toString(fileReader.getTyreExpectancy());
                } else if (dataWanted[1].equals("nextTyre")) {
                    return fileReader.getNextTyre();
                } else {
                    return Float.toString(fileReader.getTyreWear());
                }
            } else if (dataWanted[1].equals("racing")) {
                return fileReader.getRacing();
            }
        } else if (fileReader.isDriver(dataWanted[0])) {
                if (dataWanted[1].equals("previousLap")) {
                    return Float.toString(fileReader.getPreviousLap(dataWanted[0]));
                }
        }
        return null;
    }

    public String getResponseData(String aQuery, String someData) {
        String[] query = aQuery.split("\\.");
        if (query.length == 3) {
            if (query[1].equals("deltaTo")) {
                if (query[0].equals(fileReader.getUsername())) {
                    return "Time to " + query[2] + " is " + someData + " seconds.";
                } else {
                    return "The gap between " + query[0] + " and " + query[2] + " is " + someData + " seconds."; 
                }
            }
        } else if (query.length == 2) {
            if (query[1].equals("carPosition")) {
                if (query[0].equals(fileReader.getUsername())) {
                    return "You're currently P" + someData;
                } else {
                    return query[0] + " is currently P" + someData;
                }
            }
        }
        return null;
    }

    public String findDriverNameInSentence(String sentence) {
        String[] wordsInMessage = sentence.split("\\s+");
                for (String string : wordsInMessage) {
                    for (String driver : fileReader.getDriverList()) {
                        if (string.contains(driver)) {
                            return driver;
                        }
                    }
                }
        return null;
    }

    public void interactionMenu(int input) {
        boolean exit = false;
        while (!exit) {
            System.out.println(
            "How can I help?\nOption 1: Default Query\nOption 2: Custom Query\nOption 3: Exit");
            int option = getValidOption();

            if (option == 1) {
                defualtQueryMenu();
            } else if (option == 2) {

            } else if (option == 3) {
                exit = true;
            }
        }
    }

    public void printQueries() {
        System.out.println("Queries:");
        try (BufferedReader br = new BufferedReader(new java.io.FileReader(this.queriesFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String defualtQueryMenu() {
        // @TODO create a list of queries
        printQueries();
        int option = getValidOption(getFileLength(queriesFile));

        switch (option) {
            case 1: // Gap to driver in front?
                return radio("Gap to driver in front");
            case 2: // Gap to driver behind?
                return radio("Gap to driver behind");
            case 3: // Time gained/lost to driver in front
                return radio("Time differential to driver in front");
            case 4: // Time gained/lost to driver behind
                return radio("Time differential to driver behind");
            case 5: // Current position
                return radio("My current position");
            case 6: // Last Lap Time
                return radio("My last lap time");
            case 7: // Fastest lap of session
                return radio("Fastest lap of the session");
            case 8: // Difference between fastest lap and my last lap
                return radio(" Difference between fastest lap and my previous lap");
            case 9: // What is my tyre wear?
                return radio("My tyre wear");
            case 10: // What is my expected tyre life?
                return radio("What is my expected tyre life");
            case 11: // How many laps until the pit window
                return radio("How many laps till pit window");
            case 12: // Who am I racing?
                return radio("Who am I racing");

            default:
                return ("Invalid input");

        
                
        }

        
            
    }

    public int getValidOption() {
        Scanner scanner = new Scanner(System.in);
        int input;

        do {
            try {
                System.out.print("Enter option number:");
                input = scanner.nextInt();

                // Check if the entered integer is in the specified range
                if (input >=1 && input <=3) {
                    // If in range, return the integer
                    break;
                } else {
                    // If not in range, display an error message
                    System.out.println("Invalid input. Please enter the option number you wish to select.");
                }
            } catch (InputMismatchException e) {
                // If an invalid input is caught, display an error message
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine();
                input = -1; // Assigning a dummy value to input to continue the loop
            }
        } while (true);
        scanner.close();
        return input;
    }

    public int getValidOption(int range) {
        Scanner scanner = new Scanner(System.in);
        int input;

        do {
            try {
                System.out.print("Enter option number:");
                input = scanner.nextInt();

                // Check if the entered integer is in the specified range
                if (input >= 1 && input <= range) {
                    // If in range, return the integer
                    break;
                } else {
                    // If not in range, display an error message
                    System.out.println("Invalid input. Please enter the option number you wish to select.");
                }
            } catch (InputMismatchException e) {
                // If an invalid input is caught, display an error message
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine();
                input = -1; // Assigning a dummy value to input to continue the loop
            }
        } while (true);
        scanner.close();
        return input;

    }

    public int getFileLength(String filename) {
        int lineCount = 0;

        try (BufferedReader br = new BufferedReader(new java.io.FileReader(filename))) {
            // Read lines from the file until reaching the end
            while (br.readLine() != null) {
                // Increment line count for each line read
                lineCount++;
            }
        } catch (IOException e) {
            // Handle file IO exception
            e.printStackTrace();
        }
        return lineCount;
    } 
    public static void main(String[] args) {
        FileReader fileReader = new FileReader();
        fileReader.setDriversList();

        RaceEngineer jeff = new RaceEngineer();

        System.out.println(jeff.radio("What is time to HAMILTON?"));
    }
        
}
