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
                PacketLapData lapDataPacket = new PacketLapData();
                PacketParticipantData participantDataPacket = new PacketParticipantData();
                PacketCarTelemetryData carTelemetryDataPacket = new PacketCarTelemetryData();
                
                PacketDecoder packetDecoder = new PacketDecoder(receivedData, lapDataPacket, participantDataPacket, carTelemetryDataPacket);
                Packet somePacket = packetDecoder.buildPacket();
                String stringToSave = somePacket.toString();
                String fileNameLocation = "file.dat";
                if (somePacket.getHeader().getPacketId() == 2) {
                    fileNameLocation = "lapData.dat"; 
                } else if (somePacket.getHeader().getPacketId() == 4) {
                    fileNameLocation = "participantData.dat";
                // }
                } else if (somePacket.getHeader().getPacketId() == 6) {
                    fileNameLocation = "carTelemetryData.dat";
                }
                
                FileOutputStream newOutputStream = new FileOutputStream(fileNameLocation);
                newOutputStream.write(stringToSave.getBytes());
                newOutputStream.close();

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
                packetBuffer.getNextUInt16AsInt();
		        packetBuffer.getNextUInt8AsInt();
                packetBuffer.getNextUInt8AsInt();
                packetBuffer.getNextUInt8AsInt();
                packetBuffer.getNextUInt8AsInt();
                int packetId = packetBuffer.getNextUInt8AsInt();
                packetBuffer.getNextUInt64AsBigInteger();
                float sessionTime = packetBuffer.getNextFloat();
                long frameIdentifier = packetBuffer.getNextUIntAsLong();
                long overallFrameIdentifier = packetBuffer.getNextUIntAsLong();
                int playerCarIndex = packetBuffer.getNextInt8AsInt();
                
                switch (packetId) {
                    case 1:
                        noOfPacket1++;
                        break;
                    case 2:
                        noOfPacket2++;
                        break;
                    case 3:
                        noOfPacket3++;
                        break;
                    case 4:
                        noOfPacket4++;
                        break;
                    case 5:
                        noOfPacket5++;
                        break;
                    case 6:
                        noOfPacket6++;
                        break;
                    
                    case 7:
                        noOfPacket7++;
                        break;
                    case 8:
                        noOfPacket8++;
                        break;
                    case 9:
                        noOfPacket9++;
                        break;
                    case 10:
                        noOfPacket10++;
                        break;
                    
                    case 11:
                        noOfPacket11++;
                        break;
                    case 12:
                        noOfPacket12++;
                        break;
                }

                // System.out.println("Amount of packets: " + packetsReceived);
                // System.out.println(" ");
                // System.out.print("Packet 1 count: " + noOfPacket1); System.out.print(" Packet 2 count: " + noOfPacket2); System.out.print(" Packet 3 count: " + noOfPacket3);
                // System.out.print("\nPacket 4 count: " + noOfPacket4); System.out.print(" Packet 5 count: " + noOfPacket5); System.out.print(" Packet 6 count: " + noOfPacket6);
                // System.out.print("\nPacket 7 count: " + noOfPacket7); System.out.print(" Packet 8 count: " + noOfPacket8); System.out.print(" Packet 9 count: " + noOfPacket9);
                //  System.out.print("\nPacket 10 count: " + noOfPacket10);  System.out.print(" Packet 11 count: " + noOfPacket11); System.out.print(" Packet 12 count: " + noOfPacket12);
                // System.out.println("");
                // System.out.println("-------------------------------------------------------------------------------------------------------");
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




