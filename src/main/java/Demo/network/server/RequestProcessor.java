package Demo.network.server;

import java.io.*;
import java.net.Socket;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @description:
 * @author: su
 * @date: 2020/2/8
 */
public class RequestProcessor implements Runnable {
    private final static Logger logger = Logger.getLogger(
            RequestProcessor.class.getCanonicalName());
    private File rootDirectory;
    private String indexFileName = "index.html";
    private Socket connection;

    public RequestProcessor(File rootDirectory, String indexFileName, Socket connection) {
        if (rootDirectory.isFile()) {
            throw new IllegalArgumentException(
                    "rootDirectory must be a directory, not a file");
        }

        try {
            rootDirectory = rootDirectory.getCanonicalFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.rootDirectory = rootDirectory;

        if (indexFileName != null) this.indexFileName = indexFileName;
        this.connection = connection;
    }

    @Override
    public void run() {
        String root = rootDirectory.getPath();
        try {
            OutputStream raw = new BufferedOutputStream(
                    connection.getOutputStream());
            Writer out = new OutputStreamWriter(raw);
            Reader in = new InputStreamReader(new BufferedInputStream(
                    connection.getInputStream()), StandardCharsets.US_ASCII);
            StringBuilder requestLine = new StringBuilder();
            while (true) {
                int c = in.read();
                if (c == '\r' || c == '\n' ||c == -1) break;
                requestLine.append((char) c);
            }
            String get = requestLine.toString();

            logger.info(connection.getRemoteSocketAddress() + " " + get);
            String[] token = get.split("\\s+");
            String method = token[0];
            String version = "";
            if (token.length > 2) {
                version = token[2];
            }
            if (method.equals("GET")) {
                String fileName = token[1];
                if (fileName.endsWith("/")) fileName += indexFileName;
                String contentType = URLConnection.getFileNameMap()
                        .getContentTypeFor(fileName);

                File theFile = new File(rootDirectory, fileName.substring(1));

                if (theFile.canRead()
                        && theFile.getCanonicalPath().startsWith(root)) {
                    byte[] theData = Files.readAllBytes(theFile.toPath());
                    if (version.startsWith("HTTP/")) {
                        sendHeader(out, "HTTP/1.0 200 OK", contentType, theData.length);
                    }
                    raw.write(theData);
                    raw.flush();
                } else {
                    String body = new StringBuilder("<HTML>\r\n")
                            .append("<HEAD><TITLE>File Not Found</TITLE>\r\n")
                            .append("</HEAD>\r\n")
                            .append("<BODY>")
                            .append("<H1>HTTP Error 404: File Not Found</H1>\r\n")
                            .append("</BODY></HTML>\r\n").toString();
                    if (version.startsWith("HTTP/")) { // send a MIME header
                        sendHeader(out, "HTTP/1.0 404 File Not Found",
                                "text/html; charset=utf-8", body.length());
                    }
                    out.write(body);
                    out.flush();
                }
            } else {
                String body = new StringBuilder("<HTML>\r\n")
                        .append("<HEAD><TITLE>Not Implemented</TITLE>\r\n")
                        .append("</HEAD>\r\n")
                        .append("<BODY>")
                        .append("<H1>HTTP Error 501: Not Implemented</H1>\r\n")
                        .append("</BODY></HTML>\r\n").toString();
                if (version.startsWith("HTTP/")) {
                    sendHeader(out, "HTTP/1.0 501 Not Implemented"
                            ,"text/html; charset=utf-8", body.length());
                    out.write(body);
                    out.flush();
                }
            }

        } catch (IOException e) {
            logger.log(Level.WARNING,
                    "Error talking to " + connection.getRemoteSocketAddress(), e);
        }finally {
            try {
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendHeader(Writer out, String responseCode
            , String contentType, int length) throws IOException {
        out.write(responseCode + "\r\n");
        Date now = new Date();
        out.write("Date: " + now + "\r\n");
        out.write("Server: JHTTP 2.0\r\n");
        out.write("Content-length: " + length + "\r\n");
        out.write("Content-type: " + contentType + "\r\n\r\n");
        out.flush();
    }
}
