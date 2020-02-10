package Demo.network.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @description:
 * @author: su
 * @date: 2020/2/10
 */
public class UDPEchoServer extends UDPServer{
    public static final int DEFAULT_PORT = 7;

    public UDPEchoServer() {
        super(DEFAULT_PORT);
    }

    @Override
    public void respond(DatagramSocket socket, DatagramPacket request)
            throws IOException {
        DatagramPacket outgoing = new DatagramPacket(request.getData()
                , request.getLength(), request.getAddress()
                , request.getPort());
        socket.send(outgoing);
    }

    public static void main(String[] args) {
        new Thread(new UDPEchoServer()).start();
    }
}
