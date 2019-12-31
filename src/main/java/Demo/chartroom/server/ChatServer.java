package Demo.chartroom.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

public class ChatServer implements Runnable {
    /**
     * 选择器
     */
    private Selector selector;

    /**
     * 注册ServerSocketChannel后的选择键
     */
    private SelectionKey serverKey;
    /**
     * 聊天室中的用户名列表
     */
    private Vector<String> unames;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * 标识是否允许
     */
    private boolean isRun;

    public ChatServer(int port) {
        isRun = true;
        unames = new Vector<>();
        init(port);
    }

    private void init(int port) {
        try {
            selector = Selector.open();
            ServerSocketChannel serverChannel = ServerSocketChannel.open();
            serverChannel.bind(new InetSocketAddress(port));
            serverChannel.configureBlocking(false);
            serverKey = serverChannel.register(selector, SelectionKey.OP_ACCEPT);
            printInfo("server starting...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printInfo(String str) {
        System.out.println("{" + sdf.format(new Date()) + "]->" + str);
    }

    @Override
    public void run() {
        try {
            //轮询选择器选择键
            while (isRun) {
                //选择一组已准备进行IO操作的通过的key
                int n = selector.select();
                if (n > 0) {
                    //获取迭代器
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        if (key.isAcceptable()) {
                            iterator.remove();
                            ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
                            SocketChannel channel = serverChannel.accept();
                            if (channel == null) {
                                continue;
                            }
                            channel.configureBlocking(false);
                            channel.register(selector, SelectionKey.OP_READ);
                        }
                        if (key.isReadable()) {
                            readMsg(key);
                        }
                        if (key.isWritable()) {
                            writeMsg(key);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readMsg(SelectionKey key) throws IOException {
        //获取此key对应的套接字通道
        SocketChannel channel = (SocketChannel) key.channel();
        //创建一个大小为1024k的缓存区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        StringBuffer sb = new StringBuffer();
        //将通道的数据读到缓存区
        int count = channel.read(buffer);
        if (count > 0) {
            buffer.flip();
            sb.append(new String(buffer.array(), 0, count));
        }
        String str = sb.toString();
        if (str.contains("open_")) {
            String name = str.substring(5);
            printInfo(name + "online");
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                if (selectionKey != serverKey) {
                    selectionKey.attach(unames);
                    selectionKey.interestOps(selectionKey.interestOps() | SelectionKey.OP_WRITE);
                }
            }
        } else if (str.contains("exit_")) {
            String uname = str.substring(5);
            unames.remove(uname);
            key.attach("close");
            key.interestOps(SelectionKey.OP_WRITE);
            Iterator<SelectionKey> iterator = key.selector().selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                if (selectionKey != serverKey && selectionKey != key) {
                    selectionKey.attach(unames);
                    selectionKey.interestOps(selectionKey.interestOps() | SelectionKey.OP_WRITE);
                }
                printInfo(uname + " offline");
            }
        } else {
            String uname = str.substring(0, str.indexOf("^"));
            String msg = str.substring(str.indexOf("^") + 1);
            printInfo("(" + uname + ")说： " + msg);
            String dateTime = sdf.format(new Date());
            String smsg = uname + " " + dateTime + "\n " + msg + "\n";
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                if (selectionKey != serverKey) {
                    selectionKey.attach(smsg);
                    selectionKey.interestOps(selectionKey.interestOps() | SelectionKey.OP_WRITE);
                }
            }
        }
    }

    private void writeMsg(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        Object obj = key.attachment();
        key.attach("");
        if (obj.toString().equals("close")) {
            key.cancel();
            channel.socket().close();
            return;
        } else {
            channel.write(ByteBuffer.wrap(obj.toString().getBytes()));
        }
        key.interestOps(SelectionKey.OP_READ);
    }

    public static void main(String[] args) {
        ChatServer server = new ChatServer(8899);
        new Thread(server).start();
    }
}
