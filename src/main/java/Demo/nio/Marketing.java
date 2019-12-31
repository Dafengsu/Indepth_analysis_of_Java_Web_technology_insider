package Demo.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.GatheringByteChannel;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Marketing {
    private static final String DEMOGRAPHIC = "blahblah.txt";

    public static void main(String[] args) throws IOException {
        int reps = 10;
        if (args.length > 0) {
            reps = Integer.parseInt(args[0]);
        }
        FileOutputStream fos = new FileOutputStream(DEMOGRAPHIC);
        GatheringByteChannel gatherChannel = fos.getChannel();
        ByteBuffer[] bs = utterBs(reps);
        while (gatherChannel.write(bs) > 0) {

        }
        System.out.println ("Mindshare paradigms synergized to "
                + DEMOGRAPHIC);
        fos.close( );
    }

    private static ByteBuffer[] utterBs(int howMany) throws UnsupportedEncodingException {
        List<ByteBuffer> list = new LinkedList<>();
        for (int i = 0; i < howMany; i++) {
            list.add(pickRandom(col1, " "));
            list.add(pickRandom(col2, " "));
            list.add(pickRandom(col3, newline));
        }
        ByteBuffer[] buffers = new ByteBuffer[list.size()];
        list.toArray(buffers);

        return buffers;
    }

    private static ByteBuffer pickRandom(String[] strings, String suffix) throws UnsupportedEncodingException {
        String string = strings[random.nextInt(strings.length)];
        int total = string.length() + suffix.length();
        ByteBuffer buffer = ByteBuffer.allocate(total);

        buffer.put(string.getBytes(StandardCharsets.US_ASCII));
        buffer.put(suffix.getBytes(StandardCharsets.US_ASCII));
        buffer.flip();
        return buffer;
    }

    private static Random random = new Random();
    private static String [] col1 = {
            "Aggregate", "Enable", "Leverage",
            "Facilitate", "Synergize", "Repurpose",
            "Strategize", "Reinvent", "Harness"
    };
    private static String [] col2 = {
            "cross-platform", "best-of-breed", "frictionless",
            "ubiquitous", "extensible", "compelling",
            "mission-critical", "collaborative", "integrated"
    };
    private static String [] col3 = {
            "methodologies", "infomediaries", "platforms",
            "schemas", "mindshare", "paradigms",
            "functionalities", "web services", "infrastructures"
    };
    private static String newline = System.getProperty ("line.separator");

}
