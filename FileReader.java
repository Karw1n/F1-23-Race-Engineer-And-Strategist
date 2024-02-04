import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class FileReader{
    private String carTelemetryDataFile = "carTelemetry.dat";
    private String lapDataFile = "lapData.dat";
    private String participantDataFile = "participantData.dat";
    private static List<String> drivers = new ArrayList<>();
    private String username;

    public FileReader() {
        this.setDriversList();
    }

    public void setDriversList() {
        try (FileInputStream fileInputStream = new FileInputStream(this.participantDataFile);
             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

            String line;
            
            // Read each line from the file until the end of the file is reached
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains("AiControlled : 0")) {
                    while ((line = bufferedReader.readLine()) != null) {          
                        if (line.trim().startsWith("Name")) {                
                            String[] parts = line.split(":");
                            if (parts.length == 2) {
                                String name = parts[1].trim();
                                setUserame(name);
                                getDriverList().add(name); 
                                break; 
                            }
                        }
                    }    
                } 
                
                if (line.length() >= 4 && line.substring(0, 4).equals("Name")) {
                    String[] parts = line.split(":");
                    if (parts.length == 2) {
                        String name = parts[1].trim();
                        getDriverList().add(name);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setUserame(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public List<String> getDriverList() {
        return drivers;
    }

    public Boolean isDriver(String driverName) {
        return drivers.contains(driverName);
    }

    public Integer getCarPosition() {
        return Integer.parseInt(improvedFunction("CarPosition"));
    }

    public Integer getCarPosition(String driverName) {
        return Integer.parseInt(improvedFunction("CarPosition", driverName));
    }

    public String getDriver(int position) {
        String driverName = "";
        try (FileInputStream fileInputStream = new FileInputStream(this.lapDataFile);
             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    if (line.contains("Driver Name")) {
                        String[] namePart = line.split(":");
                        driverName = namePart[1].trim();
                        while ((line = bufferedReader.readLine()) != null) {          
                            if (line.trim().startsWith("CarPosition")) {                
                                String[] parts = line.split(":");
                                if (parts.length == 2) {
                                    String carPosition = parts[1].trim();
                                    if (Integer.parseInt(carPosition) == position) {
                                        return (driverName);
                                    } else {
                                        break;
                                    }  
                                }
                            }
                        }    
                    }
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return driverName;
    }
    
    public float getDeltaToCarInFront() {
        return Integer.parseInt(improvedFunction("DeltaToCarInFrontInMS"))/1000;
    }

    public float getDeltaToCarInFront(String driver) {
        return Integer.parseInt(improvedFunction("DeltaToCarInFrontInMS", driver))/1000;
    }

    public float getDeltaToRaceLeader(String fromDriver) {
        return Integer.parseInt(improvedFunction("DeltaToRaceLeaderInMs", fromDriver))/1000;
    }

    public float getDeltaToRaceLeader() {
        return Integer.parseInt(improvedFunction("DeltaToRaceLeaderInMs"))/1000;   
    }
    
    public float getDeltaToDriver(String driver) {

        float deltaToDriver = Integer.parseInt(improvedFunction("DeltaToRaceLeaderInMS", this.username))
                            - Integer.parseInt(improvedFunction("DeltaToRaceLeaderInMS", driver));
        
        return deltaToDriver/1000;

    }

    public String improvedFunction(String dataToAcquire, String driver) {
        try (FileInputStream fileInputStream = new FileInputStream(this.lapDataFile);
                 InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                 BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        // If the line contains the driver's name
                        if (line.contains(driver)) {
                            while ((line = bufferedReader.readLine()) != null) {  
                            // The line starts with the data we want to acquire (musn't be case sensitive)        
                                if (line.trim().startsWith(dataToAcquire)) { 
                                        String[] parts = line.split(":");
                                        if (parts.length == 2) {
                                            String data = parts[1].trim();
                                            return data; 
                                       }
                                }
                            }    
                        }
                    }
            } catch (IOException e) {
                e.printStackTrace();
            }
        return null;
    }

    public String improvedFunction(String dataToAcquire) {
        try (FileInputStream fileInputStream = new FileInputStream(this.lapDataFile);
                 InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                 BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        // If the line contains the driver's name
                        if (line.contains(this.username)) {
                            while ((line = bufferedReader.readLine()) != null) {  
                            // The line starts with the data we want to acquire (musn't be case sensitive)        
                                if (line.trim().startsWith(dataToAcquire)) { 
                                        String[] parts = line.split(":");
                                        if (parts.length == 2) {
                                            String data = parts[1].trim();
                                            return data; 
                                       }
                                }
                            }    
                        }
                    }
            } catch (IOException e) {
                e.printStackTrace();
            }
        return null;
    }

    public static void main(String[] args) throws IOException { 
        FileReader fileReader = new FileReader();

        //fileReader.setDriversList();
        // for (String string : fileReader.getDriverList()) {
        //     System.out.println(string);
        // }
        // System.out.println("The user's driver name is: " + fileReader.getUsername());

        System.out.println(fileReader.getCarPosition("SAINZ"));
        System.out.println(fileReader.getDriver(10));
        System.out.println(fileReader.getDeltaToDriver("HAMILTON"));

    }
}

