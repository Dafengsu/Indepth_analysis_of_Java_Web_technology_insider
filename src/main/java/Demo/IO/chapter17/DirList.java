package Demo.IO.chapter17;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/14 14:47
 */
public class DirList {
    private File directory;
    private int indent;
    private static List<File> seen = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        DirList dl = new DirList(args[0]);
        dl.list();
    }

    private void list() throws IOException {
        if (!seen.contains(this.directory)) {
            seen.add(this.directory);
            String[] files = directory.list();
            StringBuilder spaces = new StringBuilder();
            for (int i = 0; i < indent; i++) {
                spaces.append("--");
            }
            assert files != null;
            for (String file : files) {
                File f = new File(directory, file);
                if (f.isFile()) {
                    System.out.println(spaces + f.getName());
                } else {
                    DirList dl = new DirList(f, indent + 2);
                    dl.list();
                }
            }
        }
    }

    public DirList(String name) throws IOException {
        this(new File(name), 2);
    }

    public DirList(File directory, int indent) throws IOException {
        if (directory.isDirectory()) {
            this.directory = new File(directory.getCanonicalPath());
        } else {
            throw new IOException(directory.toString() + " is not a directory");
        }
        this.indent = indent;
        StringBuilder spaces = new StringBuilder();
        for (int i = 0; i < indent - 2; i++) {
            spaces.append("--");
        }
        System.out.println(spaces.toString() + this.directory + File.separatorChar);
    }
}
