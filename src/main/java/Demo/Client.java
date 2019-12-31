package Demo;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8899);
        new Thread(new SendMessageHandler(socket)).start();
        new Thread(new ReceiveMessageHandler(socket)).start();
    }
}
