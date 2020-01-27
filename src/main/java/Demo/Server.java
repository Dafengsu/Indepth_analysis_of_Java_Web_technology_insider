package Demo;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8899);
        Socket socket = serverSocket.accept();
        new Thread(new SendMessageHandler(socket)).start();
        new Thread(new ReceiveMessageHandler(socket)).start();
    }


}
class SendMessageHandler implements Runnable {
    private Socket socket;

    public SendMessageHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            OutputStream outputStream = socket.getOutputStream();
            Scanner in = new Scanner(System.in);
            PrintWriter out = new PrintWriter(new OutputStreamWriter(outputStream), true);
            while (in.hasNextLine()) {
                out.println(in.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ReceiveMessageHandler implements Runnable {
    private Socket socket;

    public ReceiveMessageHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
