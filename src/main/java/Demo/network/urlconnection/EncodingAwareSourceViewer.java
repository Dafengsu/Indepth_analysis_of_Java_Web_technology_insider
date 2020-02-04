package Demo.network.urlconnection;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @description:
 * @author: su
 * @date: 2020/2/3
 */
public class EncodingAwareSourceViewer {
    public static void main(String[] args) {
        for (String arg : args) {
            try {
                String encoding = "UTF-8";
                URL u = new URL(arg);
                URLConnection uc = u.openConnection();
                String contentType = uc.getContentType();
                int encodingStart = contentType.indexOf("charset=");
                if (encodingStart != -1) {
                    encoding = contentType.substring(encodingStart + 8);
                }
                InputStream in = new BufferedInputStream(uc.getInputStream());
                Reader r = new InputStreamReader(in, encoding);
                int c;
                while ((c = r.read())!=-1) System.out.print((char) c);
                r.close();
            } catch (MalformedURLException e) {
                System.err.println(arg + " is not a parseable URL");
            } catch (UnsupportedEncodingException e) {
                System.err.println(
                        "Server sent an encoding Java does not support: " + e.getMessage());
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }
}
