package Demo.network.clients;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * @description:
 * @author: su
 * @date: 2020/2/5
 */
public class SocketInfo {
    public static void main(String[] args) {
        for (String arg : args) {
            try (Socket socket = new Socket(arg, 80)) {
                System.out.println("Connected to " + socket.getInetAddress()
                        + " on port " + socket.getPort() + " from port "
                        + socket.getLocalPort() + " of "
                        + socket.getLocalAddress());
            } catch (UnknownHostException ex) {
                System.err.println("I can't find " + arg);
            } catch (SocketException ex) {
                System.err.println("Could not connect to " + arg);
            } catch (IOException ex) {
                System.err.println(ex);
            }
        }
    }
}
