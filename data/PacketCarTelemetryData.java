package data;
import java.util.List;
import data.elements.CarTelemetryData;
import data.elements.Packet;

public class PacketCarTelemetryData {
    private List<CarTelemetryData> CarTelemetryDataList;

    public void setCarTelemetryData(List<CarTelemetryData> CarTelemetryDataList){
        
        this.CarTelemetryDataList = CarTelemetryDataList;
    }

    public List<CarTelemetryData> getCarTelemetryList() {
        return CarTelemetryDataList;
    }

    public String toString(){
        String string = "Lap Data Packet:";
        for (CarTelemetryData carTelemetryData : CarTelemetryDataList) {
            string += "\n-------------------\n";
            string +=  carTelemetryData.toString();
            string += "\n-------------------\n";
        }
        return string;
    }
}
