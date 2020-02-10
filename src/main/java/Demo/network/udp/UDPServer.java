package Demo.network.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @description:
 * @author: su
 * @date: 2020/2/10
 */
public abstract class UDPServer implements Runnable{
    private final int bufferSize;
    private final int port;
    private final Logger logger = Logger.getLogger(UDPServer.class.getCanonicalName());
    private volatile boolean isShutDown = false;

    public UDPServer(int bufferSize, int port) {
        this.bufferSize = bufferSize;
        this.port = port;
    }

    public UDPServer(int port) {
        this(8192, 7);
    }

    @Override
    public void run() {
        byte[] buffer = new byte[bufferSize];
        try (DatagramSocket server = new DatagramSocket(port)) {
            server.setSoTimeout(10000);
            while (true) {
                if (isShutDown) return;
                DatagramPacket incoming = new DatagramPacket(
                        buffer, buffer.length);
                try {
                    server.receive(incoming);
                    respond(server, incoming);
                }catch (SocketTimeoutException ex) {
                    if (isShutDown) return;
                } catch (IOException e) {
                    logger.log(Level.WARNING, e.getMessage(), e);
                }
            }

        } catch (IOException e) {
            logger.log(Level.SEVERE, "Could not bind to port: " + port, e);
        }
    }

    public abstract void respond(DatagramSocket socket, DatagramPacket request) throws IOException;

    public void shutDown() {
        this.isShutDown = true;
    }

}
