import java.util.List;

public class PacketLapData extends Packet {
    private List<LapData> lapDataList;

    public void setLapData(List<LapData> lapDataList){
        
        this.lapDataList = lapDataList;
    }

    public List<LapData> getLapDataList() {
        return lapDataList;
    }

    public String toString(){
        String string = "Lap Data Packet:";
        for (LapData lapData : lapDataList) {
            string += "\n-------------------\n";
            string +=  lapData.toString();
            string += "\n-------------------\n";
        }

        return string;
    }
    
    

}
