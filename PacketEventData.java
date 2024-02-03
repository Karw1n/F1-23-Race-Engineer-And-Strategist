
public class PacketEventData extends Packet{
    private String eventStringCode;
    private FastestLap fastestLap;


    public void setEventStringCode(String eventCode) {
        this.eventStringCode = eventCode;
    }

    public String getEventStringCode() {
        return eventStringCode;
    }

    //TODO think about how to store event details and read and create them

    public void setFastestLap(FastestLap fastestLap) {
        this.fastestLap = fastestLap;
    }

    public FastestLap getFastestLap(FastestLap fastestLap) {
        return fastestLap;
    }

    //TODO toString needed as well
    public String toString() {
        if (eventStringCode.equals("BUTN") || eventStringCode.equals("SPTP")) {
            return "";
        } else {
            String string = "-------------------\n";
            string += "Event code: " + eventStringCode + "\n";
            if (fastestLap != null) {
                string += fastestLap.toString();
            }
            string += "\n-------------------\n";
            return string;
        }
    }

}