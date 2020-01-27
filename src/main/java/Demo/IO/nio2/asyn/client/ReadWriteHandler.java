package Demo.IO.nio2.asyn.client;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.channels.CompletionHandler;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/21 13:16
 */
public class ReadWriteHandler implements CompletionHandler<Integer, Attachment> {

    private BufferedReader conReader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public void completed(Integer result, Attachment att) {
        if (att.isReadMode) {
            att.buffer.flip();
            int limit = att.buffer.limit();
            byte[] bytes = new byte[limit];
            att.buffer.get(bytes, 0, limit);
            String msg = new String(bytes, UTF_8);
            System.out.println("Server responded: " + msg);

            try {
                msg = "";
                while (msg.length() == 0) {
                    System.out.print("Enter message (\"end\" to quit): ");
                    msg = conReader.readLine();
                }
                if (msg.equalsIgnoreCase("end")) {
                    att.mainThd.interrupt();
                    return;
                }
            } catch (IOException e) {
                System.err.println("Unable read from console");
            }

            att.isReadMode = false;
            att.buffer.clear();
            byte[] data = msg.getBytes(UTF_8);
            att.buffer.put(data);
            att.buffer.flip();
            att.channel.write(att.buffer, att, this);
        } else {
            att.isReadMode = true;

            att.buffer.clear();
            att.channel.read(att.buffer, att, this);
        }
    }

    @Override
    public void failed(Throwable exc, Attachment att) {
        System.err.println("Server not responding");
        System.exit(1);
    }
}
