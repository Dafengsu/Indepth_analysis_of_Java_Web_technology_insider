package Demo.IO.chapter06;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/5 21:49
 */
public class MonitoredSourceViewer {
    public static void main(String[] args) {
        if (args.length > 0) {
            try {
                URL u = new URL(args[0]);
                URLConnection uc = u.openConnection();
                InputStream in = uc.getInputStream();
                ProgressMonitorInputStream pin = new ProgressMonitorInputStream(null, u.toString(), in);
                ProgressMonitor pm = pin.getProgressMonitor();
                pm.setMaximum(uc.getContentLength());
                for (int c = pin.read(); c != -1; c = pin.read()) {
                    System.out.print((char) c);
                }
                pin.close();
            } catch (MalformedURLException e) {
                System.err.println(args[0] + " is not a parseable URL");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.exit(0);
    }
}
