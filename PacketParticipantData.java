
import java.util.List;

public class PacketParticipantData extends Packet {

    private int numActiveCars;

    private List<ParticipantData> participantDataList;

    public void setNumActiveCars(int numActiveCars) {
        this.numActiveCars = numActiveCars;
    }

    public int getNumActiveCars() {
        return numActiveCars;
    }

    public void setParticipantData(List<ParticipantData> participantDataList) {
        this.participantDataList = participantDataList;
    }

    public List<ParticipantData> getParticipantDataList() {
        return participantDataList;
    }

    public String toString(){
        String string = "Participant Data Packet Information:";
        for (ParticipantData participantData : participantDataList) {
            string += "\n-------------------\n";
            string +=  participantData.toString();
            string += "\n-------------------\n";
        }
        return string;
    }
    
}
