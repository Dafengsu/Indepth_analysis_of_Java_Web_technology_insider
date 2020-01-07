package Demo.IO;

import java.io.OutputStream;
import java.util.zip.Deflater;
import java.util.zip.InflaterInputStream;

/**
 * @author dafengsu
 * @description: 验证内部类的隐藏Field: this$0指向外部类的引用
 * @date 2020/1/5 14:02
 */
public class Demo {
    public DemoRunnable demoRunnable = new DemoRunnable();
    public class DemoRunnable implements Runnable {

        @Override
        public void run() {

        }
    }

    public static void main(String[] args) {
        Demo demo = new Demo();
    }
}
