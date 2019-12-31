package Demo.nio;

import java.nio.CharBuffer;

public class CharSeq {
    public static void main(String[] args) {
        StringBuffer stringBuffer = new StringBuffer("Hello World");
        CharBuffer charBuffer = CharBuffer.allocate(20);
        CharSequence charSequence = "Hello World";

        printCharSequence(charSequence);
        charSequence = stringBuffer;
        stringBuffer.setLength(0);
        stringBuffer.append("Goodbye cruel world");
        printCharSequence(charSequence);

        charSequence = charBuffer;
        charBuffer.put("xxxxxxxxxxxx");
        charBuffer.clear();
        charBuffer.put("Hello World");
        charBuffer.flip();
        printCharSequence(charSequence);

        charBuffer.mark();
        charBuffer.put("Seeya");
        charBuffer.reset();
        printCharSequence(charSequence);

        charBuffer.clear();
        printCharSequence(charSequence);
    }

    private static void printCharSequence(CharSequence cs) {
        System.out.println("length=" + cs.length()
                + ",content='" + cs.toString() + "'");
    }
}
