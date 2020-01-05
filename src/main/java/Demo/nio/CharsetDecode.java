package Demo.nio;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;

public class CharsetDecode {
    public static void main(String[] args) throws IOException {
        String charsetName = "ISO-8859-1";
        if (args.length > 0) {
            charsetName = args[0];
        }
        decodeChannel(Channels.newChannel(System.in), new OutputStreamWriter(System.out),
                Charset.forName(charsetName));
    }

    private static void decodeChannel(ReadableByteChannel source, Writer writer, Charset charset) throws IOException {
        CharsetDecoder decoder = charset.newDecoder();

        decoder.onMalformedInput(CodingErrorAction.REPLACE)
                .onUnmappableCharacter(CodingErrorAction.REPLACE);

        ByteBuffer bb = ByteBuffer.allocateDirect(16 * 1024);
        CharBuffer cb = CharBuffer.allocate(57);

        CoderResult result = CoderResult.UNDERFLOW;
        boolean eof = false;

        while (!eof) {
            if (result == CoderResult.UNDERFLOW) {
                bb.clear();
                eof = (source.read(bb) == -1);
                bb.flip();
            }
            result = decoder.decode(bb, cb, eof);
            if (result == CoderResult.OVERFLOW) {
                drainCharBuf(cb, writer);
            }
            while (decoder.flush(cb) == CoderResult.OVERFLOW) {
                drainCharBuf(cb, writer);
            }
            drainCharBuf(cb, writer);
            source.close();
            writer.flush();
        }
    }

    private static void drainCharBuf(CharBuffer cb, Writer writer) throws IOException {
        cb.flip();
        if (cb.hasRemaining()) {
            writer.write(cb.toString());
        }
        cb.clear();
    }
}
