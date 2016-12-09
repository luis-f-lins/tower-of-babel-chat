import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Arrays;

class Receiver extends Packet implements Runnable {
    String message;

    Receiver(DatagramSocket socket, int MAX_LEN, InetAddress host, int port) {
        super(socket, MAX_LEN, host, port);
        this.message = null;
    }

    public void run() {
        while (true) {
            this.message = receive();
        }
    }

    public String get_message() {
        if (this.message == null) return null; // prevents race condition

        String msg = this.message;
        this.message = null;

        return msg;
    }
}
