import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Arrays;

class Receiver extends Packet implements Runnable {
    Receiver(DatagramSocket socket, int MAX_LEN, InetAddress host, int port) {
        super(socket, MAX_LEN, host, port);
    }

    public void run() {
        while (true) {
            this.receive_message();
        }
    }

    public String get_message() {
        String msg = this.message;
        this.message = null;

        return msg;
    }

    private void receive_message() {
        this.packet = new DatagramPacket(this.buffer, this.buffer.length, this.host, this.port);
        receive();
        this.message = new String(this.buffer);
        Arrays.fill(this.buffer, (byte) 0);
    }
}
