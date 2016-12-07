import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Arrays;

class Sender extends Packet {
    Sender(DatagramSocket socket, int MAX_LEN, InetAddress host, int port) {
        super(socket, MAX_LEN, host, port);
    }

    public void send_message(String message) {
        if (message == null) return;
        this.message = message;
        this.buffer = this.message.getBytes();
        this.packet = new DatagramPacket(this.buffer, this.buffer.length, this.host, this.port);
        send();
        this.message = null;
        Arrays.fill(this.buffer, (byte) 0);
    }
}
