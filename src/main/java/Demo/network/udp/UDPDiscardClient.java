package Demo.network.udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

/**
 * @description:
 * @author: su
 * @date: 2020/2/10
 */
public class UDPDiscardClient {
    public static final int PORT = 9;

    public static void main(String[] args) {
        String hostname = args.length > 0 ? args[0] : "localhost";
        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress server = InetAddress.getByName(hostname);
            BufferedReader userInput = new BufferedReader(
                    new InputStreamReader(System.in));
            while (true) {
                String theLine = userInput.readLine();
                if (theLine.equals(".")) break;
                byte[] data = theLine.getBytes();
                DatagramPacket theOutput = new DatagramPacket(data, data.length, server, PORT);
                socket.send(theOutput);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
