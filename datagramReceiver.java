import java.net.*;
import java.io.*;

class datagramReceiver{
	public static void main(String[ ] args){
        try{
            int MAX_LEN = 40;
            int localPortNum = Integer.parseInt(args[0]);
            DatagramSocket mySocket = new DatagramSocket(localPortNum); byte[] buffer = new byte[MAX_LEN];
            while(true){
                DatagramPacket packet = new DatagramPacket(buffer, MAX_LEN); mySocket.receive(packet);
                String message = new String(buffer);
                System.out.println(message);
            }
        }
        catch(Exception e){e.printStackTrace( );}
	}
}
