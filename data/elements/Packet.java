package data.elements;
import java.math.BigInteger;

public class Packet {
    private Header header;
    
    public Packet() {}

    public Header getHeader(){
        return header;
    }

    public void setHeader(Header header){
        this.header = header;
    }

    public String toString(){
        return getHeader().toString();
    }
}
