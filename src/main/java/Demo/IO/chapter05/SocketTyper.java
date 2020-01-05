package Demo.IO.chapter05;

import java.io.*;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/5 14:44
 */
public class SocketTyper {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Usage: java SocketTyper url");
            return;
        }
        URL u = new URL(args[0]);
        /*if (!u.getProtocol().equals("http")) {
            System.err.println("Sorry, " + u.getProtocol() + " is not supported");
            return;
        }*/
        String host = u.getHost();
        int port = u.getPort();
        String file = u.getFile();
        if (file == null) {
            file = "/";
        }
        if (port <= 0) {
            port = 80;
        }
        try (Socket s = new Socket(host, port)) {
            String request = "GET " + file + " HTTP/1.1\r\n"
                    + "User-Agent: Mozilla/5.0\r\n"
                    + "Accept: text/*\r\n"
                    + "Host: " + host + "\r\n";

            byte[] b = request.getBytes(StandardCharsets.US_ASCII);
            OutputStream out = s.getOutputStream();
            InputStream in = s.getInputStream();

            out.write(b);
            out.flush();
            for (int c = in.read(); c != -1; c = in.read()) {
                System.out.write(c);
            }
        }
    }
}
