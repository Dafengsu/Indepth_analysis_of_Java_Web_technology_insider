package Demo.IO.chapter19;

import java.nio.charset.Charset;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/15 19:03
 */
public class AliasLister {
    public static void main(String[] args) {
        Charset.availableCharsets().values().forEach(cs -> {
            System.out.print(cs.displayName());
            System.out.print(cs.isRegistered() ? " (registered): " : " (unregistered): ");
            System.out.print(cs.name());
            for (String alias : cs.aliases()) System.out.print(", " + alias);
            System.out.println();
        });
    }
}
