package Demo.network.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Runtime.getRuntime;
import static java.util.concurrent.Executors.newFixedThreadPool;

/**
 * @description:
 * @author: su
 * @date: 2020/2/8
 */
public class SingleFileHTTPServer {
    public static final Logger logger = Logger.getLogger("SingleFileHTTPServer");

    private final byte[] content;
    private final byte[] header;
    private final int port;
    private final String encoding;

    public SingleFileHTTPServer(String data, String encoding, String mimeType, int port) throws UnsupportedEncodingException {
        this(data.getBytes(encoding), encoding, mimeType, port);
    }

    public SingleFileHTTPServer(byte[] data, String encoding, String mimeType, int port) {
        this.content = data;
        this.port = port;
        this.encoding = encoding;
        String header = "HTTP/1.0 200 OK\n\r"
                + "Server:OneFile 2.0\r\n"
                + "Content-length: " + this.content.length + "\r\n"
                + "Content-type: " + mimeType + ";charset=" + encoding + "\r\n\r\n";
        this.header = header.getBytes(StandardCharsets.US_ASCII);
    }

    public void start() {
        ExecutorService pool = newFixedThreadPool(getRuntime().availableProcessors());
        try (ServerSocket server = new ServerSocket(this.port)) {
            logger.info("Accepting connections on port " + server.getLocalPort());
            logger.info("Data to sent:");
            logger.info(new String(this.content, encoding));

            while (true) {
                try {
                    Socket connection = server.accept();
                    pool.submit(new HTTPHandler(connection));
                } catch (IOException e) {
                    logger.log(Level.WARNING, "Exception accepting connection", e);
                } catch (RuntimeException e) {
                    logger.log(Level.SEVERE, "Unexpected error", e);
                }

            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Could not start server", e);
        }
    }

    private class HTTPHandler implements Callable<Void> {
        private final Socket connection;

        public HTTPHandler(Socket connection) {
            this.connection = connection;
        }

        @Override
        public Void call() throws Exception {
            try (OutputStream out = new BufferedOutputStream(connection.getOutputStream());
                 InputStream in = new BufferedInputStream(connection.getInputStream())) {
                StringBuilder request = new StringBuilder(80);
                //read the first line only;that's all we need;
                while (true) {
                    int c = in.read();
                    if (c == '\r' || c == '\n' || c == -1) break;
                    request.append((char) c);
                }
                //if this is HTTP/1.0 or later send a MIME header
                if (request.toString().contains("HTTP/")) {
                    out.write(header);
                }
                out.write(content);
                out.flush();
            } catch (IOException e) {
                logger.log(Level.WARNING, "Error writing to client", e);
            }finally {
                connection.close();
            }
            return null;
        }
    }

    public static void main(String[] args) {
        int port;
        try {
            port = Integer.parseInt(args[1]);
            if (port < 1 || port > 65535) port = 80;
        } catch (RuntimeException e) {
            port = 80;
        }

        String encoding = "UTF-8";
        if (args.length > 2) encoding = args[2];
        try {
            Path path = Paths.get(args[0]);
            byte[] data = Files.readAllBytes(path);
            String contentType = URLConnection.getFileNameMap().getContentTypeFor(args[0]);
            SingleFileHTTPServer server = new SingleFileHTTPServer(data, encoding, contentType, port);
            server.start();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Usage: java SingleFileHTTPServer filename port encoding" +
                    "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
