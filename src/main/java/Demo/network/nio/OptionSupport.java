package Demo.network.nio;

import java.io.IOException;
import java.net.SocketOption;
import java.nio.channels.*;

/**
 * @description:
 * @author: su
 * @date: 2020/2/9
 */
public class OptionSupport {
    public static void main(String[] args) throws IOException {
        printOptions(SocketChannel.open());
//        printOptions(ServerSocketChannel.open());
        printOptions(AsynchronousSocketChannel.open());
        printOptions(AsynchronousServerSocketChannel.open());
        printOptions(DatagramChannel.open());
    }

    public static void printOptions(NetworkChannel channel) throws IOException {
        System.out.println(channel.getClass().getSimpleName() + " supports:");
        for (SocketOption<?> option : channel.supportedOptions()) {
            System.out.println(option.name() + ": " + channel.getOption(option));
        }
        System.out.println();
        channel.close();
    }


}
