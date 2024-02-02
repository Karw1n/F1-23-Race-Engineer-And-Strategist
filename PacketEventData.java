
public class PacketEventData extends Packet{
    private String eventStringCode;


    public void setEventStringCode(char[] array) {
        for (char c : array) {
            this.eventStringCode += String.valueOf(c);
        }
    }

    public String getEventStringCode() {
        return eventStringCode;
    }

    //TODO think about how to store event details and read and create them

    public void setEventDetails() {

    }

    //TODO toString needed as well

}