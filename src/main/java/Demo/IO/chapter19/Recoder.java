package Demo.IO.chapter19;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/15 19:18
 */
public class Recoder {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java Recoder inputEncoding outputEncoding <inFile >outFile");
            return;
        }
        try {
            Charset inputEncoding = Charset.forName(args[0]);
            Charset outputEncoding = Charset.forName(args[1]);
            convert(inputEncoding, outputEncoding, System.in, System.out);
        } catch (UnsupportedCharsetException e) {
            System.err.println(e.getCharsetName() + " is not supported by this Vm");
        } catch (IllegalCharsetNameException e) {
            System.err.println("Usage: java Recoder inputEncoding outputEncoding <inFile >outFile");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void convert(Charset inputEncoding, Charset outputEncoding, InputStream intStream, OutputStream outStream) throws IOException {
        ReadableByteChannel in = Channels.newChannel(intStream);
        WritableByteChannel out = Channels.newChannel(outStream);
        for (ByteBuffer inBuffer = ByteBuffer.allocate(4096); in.read(inBuffer) != -1; inBuffer.clear()) {
            inBuffer.flip();
            CharBuffer cBuffer = inputEncoding.decode(inBuffer);
            ByteBuffer outBuffer = outputEncoding.encode(cBuffer);
            while (outBuffer.hasRemaining()) {
                out.write(outBuffer);
            }
        }
    }
}
