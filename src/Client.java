import java.net.InetAddress;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.util.Arrays;

class Client {
    public static void main(String[] args){
        try {
            int MAX_LEN = 200;

            InetAddress host = InetAddress.getByName(args[0]);
            int port = Integer.parseInt(args[1]);

            DatagramSocket socket = new DatagramSocket(port);
            byte[] buffer = new byte[MAX_LEN];

            GUI gui = new GUI();
            Thread gui_thread = new Thread(gui);

            gui_thread.start();

            while(true){
                String message_send = gui.get_input();
                if (message_send != null) {
                    buffer = message_send.getBytes();
                    packet = new DatagramPacket(buffer, buffer.length, host, port);
                    socket.send(packet);
                }

                Arrays.fill(buffer, (byte) 0);
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, host, port);
                socket.receive(packet);
                String message_received = new String(buffer);
                if (message_received != null)
                    gui.add_message(message_received);
            }
        }
        catch(Exception e){e.printStackTrace( );}
    }
}
