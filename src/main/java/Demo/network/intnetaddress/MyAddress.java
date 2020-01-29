package Demo.network.intnetaddress;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.Security;

/**
 * @description:
 * @author: su
 * @date: 2020/1/29
 */
public class MyAddress {
    public static void main(String[] args) {
        try {
            System.out.println(InetAddress.getByName("www.oreilly.com").getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
