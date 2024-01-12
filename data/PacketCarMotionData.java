package data;
import java.util.List;
import data.elements.CarMotionData;

public class PacketCarMotionData {
    
    private List<CarMotionData> CarMotionDataList;

    public void setCarMotionData(List<CarMotionData> CarMotionDataList){
        
        this.CarMotionDataList = CarMotionDataList;
    }

    public List<CarMotionData> getCarMotionDataList() {
        return CarMotionDataList;
    }

    public String toString(){
        String string = "Lap Data Packet:";
        for (CarMotionData carMotionData : CarMotionDataList) {
            string += "\n-------------------\n";
            string +=  carMotionData.toString();
            string += "\n-------------------\n";
        }
        return string;
    }
}

