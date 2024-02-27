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
    private String eventDataFile = "eventData.dat";
    private String carDamageFile = "carDamageData.dat";
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
        return Integer.parseInt(findLapData("CarPosition"));
    }

    public Integer getCarPosition(String driverName) {
        return Integer.parseInt(findLapData("CarPosition", driverName));
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
        return Integer.parseInt(findLapData("DeltaToCarInFrontInMS"))/1000;
    }

    public float getDeltaToCarInFront(String driver) {
        return Integer.parseInt(findLapData("DeltaToCarInFrontInMS", driver))/1000;
    }
    
    public float getDeltaToRaceLeader() {
        return Integer.parseInt(findLapData("DeltaToRaceLeaderInMs"))/1000;   
    }

    public float getDeltaToRaceLeader(String fromDriver) {
        return Integer.parseInt(findLapData("DeltaToRaceLeaderInMs", fromDriver))/1000;
    }

    public float getDeltaToDriver(String driver) {

        float deltaToDriver = Integer.parseInt(findLapData("DeltaToRaceLeaderInMS", this.username))
                            - Integer.parseInt(findLapData("DeltaToRaceLeaderInMS", driver));
        
        return deltaToDriver/1000;
    }

    public float getPreviousLap() {
        return Integer.parseInt(findLapData("LastLapTimeInMs"));
    }

    public float getPreviousLap(String driver) {
        return Integer.parseInt(improvedFunction("LastLapTimeInMs", driver));
    }

    public float getFastestLap() {
        float fastestLap = 0;
        try (FileInputStream fileInputStream = new FileInputStream(this.eventDataFile);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
                String line;
            
                while ((line = bufferedReader.readLine()) != null) {
                    if (line.contains("Event code:") && line.contains("FTLP")) {
                        // Reset last lap time when encountering a new event code
                        fastestLap = 0;
                    } else if (line.contains("Lap Time")) {
                        // Store the last lap time encountered for the event code
                        fastestLap = Float.parseFloat(line.substring(line.lastIndexOf(":") + 1).trim());
                    }
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fastestLap;
    }

    public String getFastestLapDriver() {
        String driver = "";
        try (FileInputStream fileInputStream = new FileInputStream(this.eventDataFile);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
                String line;
            
                while ((line = bufferedReader.readLine()) != null) {
                    if (line.contains("Event code:") && line.contains("FTLP")) {
                        // Reset last lap time when encountering a new event code
                        driver = "";
                    } else if (line.contains("Driver Name")) {
                        // Store the last lap time encountered for the event code
                        driver = line.substring(line.lastIndexOf(":") + 1).trim();
                    }
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return driver;
    }
    
    public float getPreviousLapFastestLapDifference() {
        return getFastestLap() - getPreviousLap();
    }

    public int getTyreWear() {
        return (int) Double.parseDouble(getCarDamageData("Tyres Wear"));
    }

    public int getTyreWear(String driver) {
        return (int) Double.parseDouble(getCarDamageData("Tyres Wear", driver));
    }

    public int getTyreAge() {
        
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

    public String findLapData(String dataToAcquire) {
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

    public String findLapData(String dataToAcquire, String driver) {
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

    public String getCarTelemetryData(String dataToAcquire) {
        try (FileInputStream fileInputStream = new FileInputStream(this.carTelemetryDataFile);
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

    public String getCarDamageData(String dataToAcquire) {
        try (FileInputStream fileInputStream = new FileInputStream(this.carDamageFile);
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

    public String getCarDamageData(String dataToAcquire, String driver) {
        try (FileInputStream fileInputStream = new FileInputStream(this.carDamageFile);
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

