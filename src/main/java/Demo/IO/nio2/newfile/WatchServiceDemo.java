package Demo.IO.nio2.newfile;

import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.*;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/20 12:37
 */
public class WatchServiceDemo {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("usage: java WatchServiceDemo directory");
            return;
        }
        FileSystem fsDefault = FileSystems.getDefault();
        WatchService ws = fsDefault.newWatchService();
        Path dir = fsDefault.getPath(args[0]);
        dir.register(ws, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
        for (; ; ) {
            WatchKey key;
            try {
                key = ws.take();
            } catch (InterruptedException e) {
                return;
            }
            key.pollEvents().forEach((event) -> {
                WatchEvent.Kind<?> kind = event.kind();
                if (kind == OVERFLOW) {
                    System.out.println("overflow");
                    return;
                }
                Path filename = (Path) event.context();
                System.out.printf("%s: %s%n", event.kind(), filename);
            });

            boolean valid = key.reset();
            if (!valid) {
                break;
            }

        }
    }
}
