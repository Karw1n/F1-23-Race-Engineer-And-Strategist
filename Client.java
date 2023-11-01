import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client{
    private DatagramSocket datagramSocket;
    private InetAddress inetAddress; // Server's IP address 
    private byte[] buffer;

    public Client(DatagramSocket datagramSocket, InetAddress inetAddress){
        this.datagramSocket = datagramSocket;
        this.inetAddress = inetAddress;
    }
}
