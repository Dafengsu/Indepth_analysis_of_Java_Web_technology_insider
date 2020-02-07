package Demo.network.clients;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

/**
 * @description:
 * @author: su
 * @date: 2020/2/5
 */
public class Whois {
    public static final int DEFAULT_PORT = 43;
    public static final String DEFAULT_HOST = "whois.internic.net";

    private int port;
    private InetAddress host;

    public Whois(InetAddress host, int port) {
        this.host = host;
        this.port = port;
    }
    public Whois(InetAddress host) {
        this(host, DEFAULT_PORT);
    }
    public Whois(String hostname, int port)
            throws UnknownHostException {
        this(InetAddress.getByName(hostname), port);
    }
    public Whois(String hostname) throws UnknownHostException {
        this(InetAddress.getByName(hostname), DEFAULT_PORT);
    }
    public Whois() throws UnknownHostException {
        this(DEFAULT_HOST, DEFAULT_PORT);
    }

    public enum SearchFor {
        ANY("Any"), NETWORK("Network"), PERSON("Person"), HOST("Host"),
        DOMAIN("Domain"), ORGANIZATION("Organization"), GROUP("Group"),
        GATEWAY("Gateway"), ASN("ASN");
        private String label;
        private SearchFor(String label) {
            this.label = label;
        }
    }

    public enum SearchIn {
        ALL(""), NAME("Name"), MAILBOX("Mailbox"), HANDLE("!");
        private String label;
        private SearchIn(String label) {
            this.label = label;
        }
    }

    public String lookUpNames(String target, SearchFor category,
                              SearchIn group, boolean exactMatch) throws IOException {
        String suffix = "";
        if (!exactMatch) suffix = ".";
        String prefix = category.label + " " + group.label + " ";
        String query = prefix + target + suffix;
        try (Socket socket = new Socket()) {
            SocketAddress address = new InetSocketAddress(host, port);
            socket.connect(address);
            Writer out = new OutputStreamWriter(
                    socket.getOutputStream(), StandardCharsets.US_ASCII);
            BufferedReader in = new BufferedReader(new
                    InputStreamReader(socket.getInputStream(), StandardCharsets.US_ASCII));
            out.write(query + "\r\n");
            out.flush();
            StringBuilder response = new StringBuilder();
            String theLine;
            while ((theLine = in.readLine()) != null) {
                response.append(theLine);
                response.append("\r\n");
            }
            return response.toString();
        }
    }

    public InetAddress getHost() {
        return host;
    }

    public void setHost(String host) throws UnknownHostException {
        this.host = InetAddress.getByName(host);
    }
}
