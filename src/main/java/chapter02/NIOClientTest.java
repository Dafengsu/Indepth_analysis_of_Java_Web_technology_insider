package chapter02;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class NIOClientTest {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 8899);
        OutputStream out = socket.getOutputStream();
        InputStream in = socket.getInputStream();
        String s = "hello world";
        out.write(s.getBytes());
        out.close();
        byte[] buff = new byte[1024];
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        in.read(buff);
        System.out.println(new String(buff).trim());

    }
}
