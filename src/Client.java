import java.net.InetAddress;
import java.net.DatagramSocket;

class Client {
    public static void main(String[] args){
        try {
            String from_user = args[0];
            String to_user = args[1];

            InetAddress to_host = InetAddress.getByName(args[2]);
            int send_port = Integer.parseInt(args[3]);
            int receive_port = Integer.parseInt(args[4]);

            GUI gui = new GUI(from_user);
            Thread gui_thread = new Thread(gui);

            DatagramSocket socket = new DatagramSocket(receive_port);

            Packet sender = new Packet(socket, Config.MESSAGE_MAX_LEN, to_host, send_port);
            Receiver receiver = new Receiver(socket, Config.MESSAGE_MAX_LEN, to_host, receive_port);
            Thread receiver_thread = new Thread(receiver);

            Translator translator = new Translator();
            Thread translator_thread = new Thread(translator);

            receiver_thread.start();
            translator_thread.start();
            gui_thread.start();

            while(true){
                String message = receiver.get_message();
                if (message != null)
                    gui.add_message(message, to_user);

                message = gui.get_message();
                if (message != null)
                    message = translator.go(message);

                if (message == null)
                    message = translator.get_translated();

                if (message != null)
                    sender.send(message.getBytes());
            }
        } catch(Exception e){e.printStackTrace( );}
    }
}
