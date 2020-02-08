package Demo.network.ssl;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @description:
 * @author: su
 * @date: 2020/2/8
 */
public class HTTPSClient {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java HTTPSClient2 host");
            return;
        }

        int port = 443; // default https port
        String host = args[0];
        SSLSocketFactory factory
                = (SSLSocketFactory) SSLSocketFactory.getDefault();
        try (SSLSocket socket =(SSLSocket) factory.createSocket(host, port)) {
            String[] supported = socket.getSupportedCipherSuites();
            socket.setEnabledCipherSuites(supported);

            Writer out = new OutputStreamWriter(
                    socket.getOutputStream(), StandardCharsets.UTF_8);
            out.write("GET http://" + host + "/ HTTP/1.1\r\n");
            out.write("Host: " + host + "\r\n");
            out.write("\r\n");
            out.flush();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            String s;
            while (!(s = in.readLine()).equals("")) {
                System.out.println(s);
            }
            System.out.println();

            // read the length
            String contentLength = in.readLine();
            int length = Integer.MAX_VALUE;
            try {
                length = Integer.parseInt(contentLength.trim(), 16);
            } catch (NumberFormatException ignored) {

            }
            System.out.println(contentLength);
            int c;
            int i = 0;
            while ((c = in.read()) != -1 && i++ < length) {
                System.out.write(c);
            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
