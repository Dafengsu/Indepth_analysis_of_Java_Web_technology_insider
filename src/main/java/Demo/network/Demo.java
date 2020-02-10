package Demo.network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.StandardSocketOptions;
import java.nio.channels.ServerSocketChannel;

import static Demo.network.nio.OptionSupport.printOptions;

/**
 * @description:
 * @author: su
 * @date: 2020/2/4
 */
public class Demo {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.setOption(StandardSocketOptions.IP_TOS, 2);
        channel.getOption(StandardSocketOptions.IP_TOS);
    }
}
