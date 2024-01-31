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
        String dataToObtain = processQuery(question);
        String dataToFormat = retrieveData(dataToObtain);
        
        return dataToFormat;
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
            if (query.contains("current") && query.contains("my")) {
                return "user.CarPosition";
            // This condition is that the user asks about another drivers position
            // If the user asks an invalid name it will return the user
            } else {
                String returnQuery = "CarPosition";
                String[] wordsInMessage = query.split("\\s+");
                for (String string : wordsInMessage) {
                    for (String name : fileReader.getDriverList()) {
                        if ((string == name)) {
                            returnQuery += "." + name;
                        }
                        
                    }
                }
                return returnQuery;
            }
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
            }
        }
        return null;
    }


    public String getResponseData(String question) {
        String answer = "";
        if (question.contains("current") && question.contains("position")) {
            // Call the getCurrentPosition function
            answer = String.valueOf(fileReader.getCarPosition());
        }
        return answer;
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

    public static void main(String[] args) {
        FileReader fileReader = new FileReader();
        fileReader.setDriversList();

        RaceEngineer jeff = new RaceEngineer();

        System.out.println(jeff.radio("What is time to HAMILTON?"));
    }
}
