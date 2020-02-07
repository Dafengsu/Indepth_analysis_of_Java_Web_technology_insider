package Demo.network.clients;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class DictClient {

  public static final String SERVER = "dict.org";
  public static final int PORT = 2628;
  public static final int TIMEOUT = 15000;
  
  public static void main(String[] args) {

    try (Socket socket = new Socket(SERVER, PORT)) {
      socket.setSoTimeout(TIMEOUT);
      OutputStream out = socket.getOutputStream();
      Writer writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
      writer = new BufferedWriter(writer);
      BufferedReader reader = new BufferedReader(new InputStreamReader(
              socket.getInputStream(), StandardCharsets.UTF_8));

      for (String word : args) {
        define(word, writer, reader);
      }

      writer.write("quit\r\n");
      writer.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  static void define(String word, Writer writer, BufferedReader reader)
      throws IOException {
    writer.write("DEFINE fd-eng-lat " + word + "\r\n");
    writer.flush();

    for (String line = reader.readLine(); line != null; line = reader.readLine()) {
      if (line.startsWith("250 ")) { // OK
        return;
      } else if (line.startsWith("552 ")) { // no match
        System.out.println("No definition found for " + word);
        return;
      } else if (!line.matches("\\d\\d\\d .*")
              && !line.trim().equals(".")) {
        System.out.println(line);
      }
    }
  } 
}
