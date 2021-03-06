package Demo.network.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @description:
 * @author: su
 * @date: 2020/2/10
 */
public class ReceiverThread extends Thread {
    private DatagramSocket socket;
    private volatile boolean stopped = false;

    public ReceiverThread(DatagramSocket socket) {
        this.socket = socket;
    }

    public void halt() {
        this.stopped = true;
    }

    @Override
    public void run() {
        byte[] buffer = new byte[65507];
        while (true) {
            if (stopped) return;
            DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
            try {
                socket.receive(dp);
                String s = new String(dp.getData(), 0, dp.getLength(), "UTF-8");
                System.out.println(s);
                Thread.yield();
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }
}
