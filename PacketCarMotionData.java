
import java.util.List;

public class PacketCarMotionData extends Packet {
    
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

