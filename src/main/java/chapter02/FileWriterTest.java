package chapter02;

import java.io.*;

public class FileWriterTest {
    public static void main(String[] args) {
        Writer writer = null;
        try {

            writer = new FileWriter("src/main/data/test.txt");
            writer.write("测试");
            writer.append("121");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                assert writer != null;
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }
}
