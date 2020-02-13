package chapter04;

import com.sun.tools.javac.comp.Enter;
import com.sun.tools.javac.comp.MemberEnter;
import com.sun.tools.javac.jvm.Gen;
import com.sun.tools.javac.main.JavaCompiler;
import com.sun.tools.javac.main.Main;
import com.sun.tools.javac.parser.JavacParser;

/**
 * @description:
 * @author: su
 * @date: 2020/2/12
 */
public class CompilerDemo {
    public static void main(String[] args) {
        Main compiler = new Main("javac");
        compiler.compile(args);

    }
}
