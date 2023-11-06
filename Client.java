import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Client{
    private DatagramSocket datagramSocket;
    private InetAddress inetAddress; // Server's IP address 
    private byte[] buffer;

    public Client(DatagramSocket datagramSocket, InetAddress inetAddress){
        this.datagramSocket = datagramSocket;
        this.inetAddress = inetAddress;
    }

    public static void main(String[] args) throws IOException{
        int port = 20777;
        String ip = "192.168.86.199";
        DatagramSocket socket = null;
        int bufferSize = 2048;
        byte[] buffer = new byte[bufferSize];
        
        try {
            InetAddress gamInetAddress = InetAddress.getByName(ip);
            socket = new DatagramSocket(port, gamInetAddress);

            System.out.println("UDP Client started. Listening on port: " + port);

        
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()){
                socket.close();
            }
        }
    
    }

}




