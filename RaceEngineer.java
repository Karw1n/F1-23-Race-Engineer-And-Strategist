import java.util.InputMismatchException;
import java.util.Scanner;

public class RaceEngineer {
    private String driverName;
    private FileReader fileReader;


    public RaceEngineer() {
        this.fileReader = new FileReader();
        this.fileReader.setDriversList();
        this.driverName = this.fileReader.getUsername();
    }
    
    public String radio(String question) {
        // decode question
        String query = processQuery(question);
        if (query.equals("INVALID QUERY")) {
            return "INVALID QUERY";
        }
        String data = retrieveData(query);
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
        } // } else if {} // @TODO implement lap time queries

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
            }
        } else if (dataWanted[1].equals("carPosition")) {
            if (dataWanted.length == 3) {
                return Integer.toString(fileReader.getCarPosition(dataWanted[2]));
            } else {
                return Integer.toString(fileReader.getCarPosition());
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



       

        }
    }

    public String defualtQueryMenu() {
        // @TODO create a list of queries
        System.out.println("");
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

    public static void main(String[] args) {
        FileReader fileReader = new FileReader();
        fileReader.setDriversList();

        RaceEngineer jeff = new RaceEngineer();

        System.out.println(jeff.radio("What is time to HAMILTON?"));
    }
        
}
