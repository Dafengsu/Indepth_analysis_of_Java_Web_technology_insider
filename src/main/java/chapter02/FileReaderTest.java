package chapter02;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderTest {
    public static void main(String[] args) throws IOException {
        FileReader reader = null;

        try {
            StringBuffer str = new StringBuffer();
            char[] buffer = new char[1024];
            reader = new FileReader("src/main/data/test.txt");
            reader.read(buffer);
            str.append(buffer);
            System.out.println(new String(str).trim());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            reader.close();
        }
    }
}
