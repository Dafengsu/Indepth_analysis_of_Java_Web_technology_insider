package Demo.network.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @description:
 * @author: su
 * @date: 2020/2/10
 */
public class FastUDPDiscardServer extends UDPServer{

    public final static int DEFAULT_PORT = 9;
    public FastUDPDiscardServer() {
        super(DEFAULT_PORT);
    }

    public static void main(String[] args) {
        UDPServer server = new FastUDPDiscardServer();
        new Thread(server).start();
    }
    @Override
    public void respond(DatagramSocket socket, DatagramPacket request) throws IOException {

    }
}
