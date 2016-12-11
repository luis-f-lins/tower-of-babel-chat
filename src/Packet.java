import java.io.IOException;
import java.net.InetAddress;
import java.net.DatagramSocket;
import java.net.DatagramPacket;

class Packet {
    DatagramSocket socket;
    InetAddress host;
    int port;
    int MAX_LEN;

    Packet(DatagramSocket socket, int MAX_LEN, InetAddress host, int port) {
        this.socket = socket;
        this.host = host;
        this.port = port;

        this.MAX_LEN = MAX_LEN;
    }

    public String receive() {
        String message = null;

        try {
            byte[] buffer = new byte[this.MAX_LEN];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, this.host, this.port);

            this.socket.receive(packet);
            message = new String(buffer);
            if (message != null)
                LOG.debug("Receive packet received, message = " + message);
        } catch (IOException e) {
            LOG.error("IOException (receive): " + e.getMessage());
        }
        return message;
    }

    public void send(byte[] buffer) {
        try {
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, host, port);
            this.socket.send(packet);

            LOG.debug("Send packet sent, message = " + new String(buffer));
        } catch (IOException e) {
            LOG.error("IOException (send): " + e.getMessage());
        }
    }
}
