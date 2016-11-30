import java.net.*;
import java.io.*;
class datagramSender{
	public static void main(String[ ] args){
		try{
			InetAddress receiverHost = InetAddress.getByName(args[0]);
			int receiverPort = Integer.parseInt(args[1]);
			String message = args[2];
			DatagramSocket mySocket = new DatagramSocket( );
			byte[] buffer = message.getBytes( );
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length, receiverHost, receiverPort);
			mySocket.send(packet);
		}
		catch(Exception e){ e.printStackTrace( ); }
	}
}