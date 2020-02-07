package Demo.network.clients;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Date;

/**
 * @description:
 * @author: su
 * @date: 2020/2/5
 */
public class Time {
    private static final String HOSTNAME = "time.nist.gov";

    public static void main(String[] args) throws IOException {
        Date d = Time.getDateFromNetwork();
        System.out.println("It is " + d);
    }

    private static Date getDateFromNetwork() throws IOException {
        long differenceBetweenEpochs = 2208988800L;
        try (Socket socket = new Socket(HOSTNAME, 37)) {
            socket.setSoTimeout(15000);
            InputStream raw = socket.getInputStream();
            long secondsSince1900 = 0;
            for (int i = 0; i < 4; i++) {
                secondsSince1900 = (secondsSince1900 << 8) | raw.read();
            }
            long secondsSince1970 = secondsSince1900 - differenceBetweenEpochs;
            long msSince1970 = secondsSince1970 * 1000;
            Date time = new Date(msSince1970);
            return time;
        }

    }
}
