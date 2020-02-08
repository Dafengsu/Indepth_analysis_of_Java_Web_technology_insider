package Demo.network;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @description:
 * @author: su
 * @date: 2020/2/4
 */
public class Demo {
    public static void main(String[] args) {
        String s = "GET /directory/filename.html HTTP/1.0\r\n\r\n";
        BufferedReader in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(s.getBytes())));
        for (int i = 0; i < 2; i++) {
            try {
                String line = in.readLine();
                System.out.println(line);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }




    }
}
