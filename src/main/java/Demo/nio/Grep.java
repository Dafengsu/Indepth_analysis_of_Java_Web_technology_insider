package Demo.nio;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grep {
    public static void main(String[] args) {
        boolean ignoreCase = false;
        boolean onebyone = false;
        List<String> argList = new LinkedList<>();
        for (String arg : args) {
            if (arg.startsWith("-")) {
                if (arg.equals("-i") || arg.equals("--ignore-case")) {
                    ignoreCase = true;
                }
                if (arg.equals("-1")) {
                    onebyone = true;
                }
                continue;
            }
            argList.add(arg);
        }

        if (argList.size() < 2) {
            System.out.println("usage: [options] filename ...");
            return;
        }

        Grep grepper = new Grep(argList.remove(0), ignoreCase);

        if (onebyone) {
            for (String fileName : argList) {
                System.out.println(fileName + ":");
                MatchedLine[] matches;
                try {
                    matches = grepper.grep(fileName);
                } catch (IOException e) {
                    System.err.println("\t*** " + e);
                    continue;
                }
                for (MatchedLine match : matches) {
                    System.out.println(" " + match.getLineText() + "[" + match.start + "-" + (match.end - 1) + "]: " + match.getLineText());
                }

            }
        } else {
            File[] files = new File[argList.size()];
            for (int i = 0; i < files.length; i++) {
                files[i] = new File(argList.get(i));
            }
            MatchedLine[] matches = grepper.grep(files);
            for (MatchedLine match : matches) {
                System.out.println(match.getFile().getName() + ", " + match.getLineText() + ": " + match.getLineText());
            }

        }
    }
    private Pattern pattern;

    public Grep(Pattern pattern) {
        this.pattern = pattern;
    }

    public Grep(String regex, boolean ignoreCase) {
        this.pattern = Pattern.compile(regex, ignoreCase ? Pattern.CASE_INSENSITIVE : 0);
    }

    public Grep(String regex) {
        this(regex, false);
    }

    public MatchedLine[] grep(File file) throws IOException {
        List<MatchedLine> list = grepList(file);
        MatchedLine[] matches = new MatchedLine[list.size()];
        list.toArray(matches);
        return matches;
    }

    public MatchedLine[] grep(String fileName) throws IOException {
        return grep(new File(fileName));
    }

    public MatchedLine[] grep(File[] files) {
        List<MatchedLine> aggregate = new LinkedList<>();
        for (File file : files) {
            try {
                List<MatchedLine> temp = grepList(file);
                aggregate.addAll(temp);
            } catch (IOException ignored) {

            }
        }
//        MatchedLine[] matches = new MatchedLine(aggregate.size());
        return (MatchedLine[]) aggregate.toArray();
    }
    private List<MatchedLine> grepList(File file) throws IOException {
        if (!file.exists()) {
            throw new IOException("Does not exits: " + file);
        }
        if (!file.isAbsolute()) {
            throw new IOException("Not a regular file: " + file);
        }
        if (!file.canRead()) {
            throw new IOException("Unreadable file: " + file);
        }
        List<MatchedLine> list = new LinkedList<>();
        FileReader fr = new FileReader(file);
        LineNumberReader lnr = new LineNumberReader(fr);
        Matcher matcher = this.pattern.matcher("");
        String line;

        while ((line = lnr.readLine()) != null) {
            matcher.reset(line);
            if (matcher.find()) {
                list.add(new MatchedLine(file, lnr.getLineNumber(), line, matcher.start(), matcher.end()));
            }
        }
        lnr.close();
        return list;
    }
    public static class MatchedLine {
        private File file;
        private int lineNumber;
        private String lineText;
        private int start;
        private int end;

        public MatchedLine(File file, int lineNumber, String lineText, int start, int end) {
            this.file = file;
            this.lineNumber = lineNumber;
            this.lineText = lineText;
            this.start = start;
            this.end = end;
        }

        public File getFile() {
            return file;
        }

        public int getLineNumber() {
            return lineNumber;
        }

        public String getLineText() {
            return lineText;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }


    }
}

