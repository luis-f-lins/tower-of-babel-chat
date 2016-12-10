import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;

import java.util.LinkedList;
import java.util.Queue;

class Receiver extends Packet implements Runnable {
    volatile Queue<String> messages;

    Receiver(DatagramSocket socket, int MAX_LEN, InetAddress host, int port) {
        super(socket, MAX_LEN, host, port);
        this.messages = new LinkedList<String>();
    }

    public void run() {
        while (true) {
            String message = receive();
            if (message != null)
                this.messages.add(message);
        }
    }

    public String get_message() {
        if (this.messages.isEmpty()) return null;
        return this.messages.remove();
    }
}
