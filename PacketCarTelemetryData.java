
import java.util.List;

public class PacketCarTelemetryData extends Packet {
    private List<CarTelemetryData> CarTelemetryDataList;

    public void setCarTelemetryData(List<CarTelemetryData> CarTelemetryDataList){
        
        this.CarTelemetryDataList = CarTelemetryDataList;
    }

    public List<CarTelemetryData> getCarTelemetryList() {
        return CarTelemetryDataList;
    }

    public String toString(){
        String string = "Car Telemetry Data Packet:";
        for (CarTelemetryData carTelemetryData : CarTelemetryDataList) {
            string += "\n-------------------\n";
            string +=  carTelemetryData.toString();
            string += "\n-------------------\n";
        }
        return string;
    }
}
