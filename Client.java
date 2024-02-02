import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

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
        int bufferSize = 5000;
        byte[] buffer = new byte[bufferSize];
        String fileName = "file.dat";
        int packetsReceived = 0;
        int noOfPacket1 = 0; int noOfPacket2 = 0; int noOfPacket3 = 0; int noOfPacket4 = 0; int noOfPacket5 = 0; int noOfPacket6 = 0;
        int noOfPacket7 = 0; int noOfPacket8 = 0; int noOfPacket9 = 0; int noOfPacket10 = 0; int noOfPacket11 = 0; int noOfPacket12 = 0;

        
        
        
        try {
            InetAddress gameInetAddress = InetAddress.getByName(ip);
            socket = new DatagramSocket(port, gameInetAddress);

            System.out.println("UDP Client started. Listening on port: " + port);
            
            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                
                socket.receive(packet);
                
                
                // need to check if whole packet is being read
                packetsReceived ++;
                // Process the received data
                byte[] receivedData = packet.getData();
                
                PacketBuffer packetBuffer = new PacketBuffer(receivedData);
                ByteBuffer test = ByteBuffer.wrap(receivedData);
               
                //PacketDecoder packetDecoder = new PacketDecoder(receivedData, lapDataPacket, participantDataPacket, carTelemetryDataPacket);
                PacketDecoder packetDecoder = new PacketDecoder(receivedData);
                Packet somePacket = packetDecoder.buildPacket();
                String stringToSave = somePacket.toString();
                String fileNameLocation = "file.dat";
                if (somePacket.getHeader().getPacketId() == 2) {
                    fileNameLocation = "lapData.dat"; 
                } else if (somePacket.getHeader().getPacketId() == 3) { 
                    fileNameLocation = "eventData.dat";
                }else if (somePacket.getHeader().getPacketId() == 4) {
                    fileNameLocation = "participantData.dat";
                // }
                } else if (somePacket.getHeader().getPacketId() == 6) {
                    fileNameLocation = "carTelemetryData.dat";
                } 
                
                if (somePacket.getHeader().getPacketId() != 3) {
                    FileOutputStream newOutputStream = new FileOutputStream(fileNameLocation);
                    newOutputStream.write(stringToSave.getBytes());
                    newOutputStream.close();
                } else {
                    FileOutputStream newOutputStream = new FileOutputStream(fileNameLocation, true);
                    newOutputStream.write(stringToSave.getBytes());
                    newOutputStream.close();
                }

                test.order(ByteOrder.LITTLE_ENDIAN);
                
                int packetLength = packet.getLength();
                //System.out.println("packetLength is: " + packetLength);
                test.position(6);
                int packetType = 0xFF & test.get();
                //System.out.println("Packet Type is: " + packetType);

                //Print Participant data list.
                
                String message = "PType: " +  (Integer.toString(packetType) + " Packet Size: " + (Integer.toString(packetLength)) + "[ "  + "  ]"  + "\n");
                byte[] fileMessage = message.getBytes();
                
            
                FileOutputStream outputStream = new FileOutputStream(fileName, true);
                outputStream.write(fileMessage);
                outputStream.close();
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




