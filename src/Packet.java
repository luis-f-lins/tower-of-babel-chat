import java.io.IOException;
import java.net.InetAddress;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.util.Arrays;

class Packet {
    DatagramSocket socket;
    DatagramPacket packet;
    InetAddress host;
    int port;
    byte[] buffer;
    volatile String message;

    Packet(DatagramSocket socket, int MAX_LEN, InetAddress host, int port) {
        this.socket = socket;
        this.buffer = new byte[MAX_LEN];
        this.message = null;
        Arrays.fill(this.buffer, (byte) 0);
        this.host = host;
        this.port = port;
    }

    public void receive() {
        try {
            this.socket.receive(this.packet);
            if (this.message != null)
                System.out.println("Receive packet received, message = " + this.message);
        } catch (IOException e) {
            System.out.println("IOException (receive): " + e);
        }
    }

    public void send() {
        try {
            this.socket.send(this.packet);
            System.out.println("Send packet sent, message = " + this.message);
        } catch (IOException e) {
            System.out.println("IOException (send): " + e);
        }
    }
}
