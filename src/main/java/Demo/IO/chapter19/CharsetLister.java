package Demo.IO.chapter19;

import java.nio.charset.Charset;
import java.util.SortedMap;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/15 18:52
 */
public class CharsetLister {
    public static void main(String[] args) {
        for (String charset : Charset.availableCharsets().keySet()) {
            System.out.println(charset);
        }
    }
}
