package Demo.network.clients;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * @description:
 * @author: su
 * @date: 2020/2/5
 */

public class DaytimeClient {

    public static void main(String[] args) {
        String hostname = args.length > 0 ? args[0] : "time.nist.gov";
        try (Socket socket = new Socket(hostname, 13)) {
            socket.setSoTimeout(15000);
            StringBuilder time = new StringBuilder();
            try (Reader r = new InputStreamReader(
                    socket.getInputStream(), StandardCharsets.US_ASCII)) {
                for (int c = r.read(); c != -1; c = r.read())
                    time.append((char) c);
            }
            System.out.println(time);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
