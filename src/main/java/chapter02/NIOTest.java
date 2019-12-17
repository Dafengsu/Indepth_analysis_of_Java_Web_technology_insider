package chapter02;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NIOTest {
    public static void main(String[] args) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
//        Selector selector = Selector.open();
//        ServerSocketChannel ssc = ServerSocketChannel.open();
//        ssc.configureBlocking(false);
//        ssc.socket().bind(new InetSocketAddress("127.0.0.1",8888));
//        ssc.register(selector, SelectionKey.OP_ACCEPT);//注册监听事件
        //1. 创建选择器
        Selector selector = Selector.open();

        //2.将通道注册到选择器上
        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        ssChannel.configureBlocking(false);
        //通道必须配置为非阻塞模式，否则使用选择器就没有任何意义了
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);

        ServerSocket ss=ssChannel.socket();
        ss.bind(new InetSocketAddress("127.0.0.1",8888));
        while (true) {
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> it = selectionKeys.iterator();//取得所有key集合
            while (it.hasNext()) {
                SelectionKey key = it.next();
                if (key.isAcceptable()) {
                    ServerSocketChannel ssChannel1 = (ServerSocketChannel) key.channel();

                    // 服务器会为每个新连接创建一个 SocketChannel
                    SocketChannel sChannel = ssChannel1.accept();
                    sChannel.configureBlocking(false);

                    // 这个新连接主要用于从客户端读取数据
                    sChannel.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    SocketChannel sc = (SocketChannel) key.channel();
                    while (true) {
                        buffer.clear();
                        int n = sc.read(buffer);
                        if (n <= 0) {
                            break;
                        }
                        buffer.flip();
                    }
                    String str = new String(buffer.array()).trim();
                    System.out.println(new String(buffer.array()));
                    sc.write(ByteBuffer.wrap(str.getBytes()));
                    it.remove();
                }
            }
        }
    }
}
