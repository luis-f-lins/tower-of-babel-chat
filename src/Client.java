import java.net.InetAddress;
import java.net.DatagramSocket;

class Client {
    public static void main(String[] args){
        try {
            int MAX_USER_LEN = 20;
            int MAX_LEN = 200;

            String from_user = args[0];
            String to_user = args[1];

            InetAddress to_host = InetAddress.getByName(args[2]);
            int send_port = Integer.parseInt(args[3]);
            int receive_port = Integer.parseInt(args[4]);

            GUI gui = new GUI(from_user);
            Thread gui_thread = new Thread(gui);

            DatagramSocket socket = new DatagramSocket(receive_port);

            Sender sender = new Sender(socket, MAX_LEN, to_host, send_port);
            Receiver receiver = new Receiver(socket, MAX_LEN, to_host, receive_port);
            Thread receiver_thread = new Thread(receiver);

            receiver_thread.start();
            gui_thread.start();

            while(true){
                gui.add_message(receiver.get_message(), to_user);
                sender.send_message(gui.get_message());
            }
        } catch(Exception e){e.printStackTrace( );}
    }
}
