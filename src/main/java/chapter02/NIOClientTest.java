package chapter02;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class NIOClientTest {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 8899);
        OutputStream out = socket.getOutputStream();
        InputStream in = socket.getInputStream();
        String s = "hello world";
        out.write(s.getBytes());
        out.close();
        Scanner scanner = new Scanner(in);
        while (scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }
    }
}
