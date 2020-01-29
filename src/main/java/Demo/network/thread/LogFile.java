package Demo.network.thread;

import java.io.*;
import java.util.Date;

/**
 * @description:
 * @author: su
 * @date: 2020/1/28
 */
public class LogFile {
    private Writer out;

    public LogFile(File f) throws IOException {
        out = new BufferedWriter(new FileWriter(f));
    }

    public synchronized void writeEntry(String message) throws IOException {
        Date d = new Date();
        out.write(d.toString());
        out.write('\t');
        out.write(message);
        out.write("\r\n");
    }

    public void close() throws IOException {
        out.flush();
        out.close();
    }
}
