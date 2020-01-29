package Demo.network.intnetaddress;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * @description:
 * @author: su
 * @date: 2020/1/29
 */
public class OReillyByName {
    public static void main(String[] args) {
        try {
            for (InetAddress address : InetAddress.getAllByName("www.baidu.com")) {
                System.out.println(address);
            }
        } catch (UnknownHostException e) {
            System.err.println("Could not find www.oreilly.com");
        }
    }
}
