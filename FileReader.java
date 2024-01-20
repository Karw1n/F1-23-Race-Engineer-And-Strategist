import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileReader{
    private String carTelemetryDataFile = "carTelemetry.dat";
    private String lapDataFile = "lapData.dat";
    private String participantDataFile = "participantData.dat";
    private static List<String> drivers = new ArrayList<>();
    private String username;

    public FileReader() {}

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

    public static void main(String[] args) throws IOException { 
        FileReader fileReader = new FileReader();

        fileReader.setDriversList();

        for (String string : fileReader.getDriverList()) {
            System.out.println(string);
        }

        System.out.println("The user's driver name is: " + fileReader.getUsername());

    }
}

