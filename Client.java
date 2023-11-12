import java.io.FileOutputStream;
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
        String ip = "127.0.0.1";
        DatagramSocket socket = null;
        int bufferSize = 1460;
        byte[] buffer = new byte[bufferSize];
        String fileName = "file.dat";
        
        
        try {
            InetAddress gameInetAddress = InetAddress.getByName(ip);
            socket = new DatagramSocket(port, gameInetAddress);
            System.out.println("UDP Client started. Listening on port: " + port);

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                // Process the received data
                byte[] receivedData = packet.getData();
                int length = packet.getLength();
                // Process the received data here
                FileOutputStream outputStream = new FileOutputStream(fileName);
                outputStream.write(receivedData);
                outputStream.close();

                System.out.println("Data buffer has been written to the file in the current directory.");

                // String receivedMessage = new String(receivedData, 0, length);
                // System.out.println("Received data: " + receivedMessage);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()){
                socket.close();
            }
        }
    }

}




