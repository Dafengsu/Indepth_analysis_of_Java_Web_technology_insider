package Demo.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.List;

public class SelectSocketsThreadPool extends SelectSockets {
    //获取最大可用线程数
    private static final int MAX_THREADS = Runtime.getRuntime().availableProcessors();
    //创建线程池
    private ThreadPool pool = new ThreadPool(MAX_THREADS);

    public static void main(String[] args) throws IOException {
        //运行父类go方法
        new SelectSocketsThreadPool().go(args);
    }

    /**
     * 重写readDataFromSocket方法，调用线程处理数据
     * @param key
     */
    @Override
    protected void readDataFromSocket(SelectionKey key) {
        WorkerThread worker = pool.getWorker();
        if (worker == null) {
            return;
        }
        worker.serviceChannel(key);
    }

    /**
     * 线程池
      */
    private class ThreadPool {
        final List<WorkerThread> idle = new LinkedList<>();

        public ThreadPool(int poolSize) {
            for (int i = 0; i < poolSize; i++) {
                WorkerThread thread = new WorkerThread(this);
                thread.setName("Worker" + (i + 1));
                thread.start();
                idle.add(thread);
            }
        }

        public void returnWorker(WorkerThread worker) {
            synchronized (idle) {
                idle.add(worker);
            }
        }


        WorkerThread getWorker() {
            WorkerThread worker = null;
            synchronized (idle) {
                if (idle.size() > 0) {
                    worker = idle.remove(0);
                }

                return worker;
            }
        }
    }

    /**
     * Worker
     */
    private class WorkerThread extends Thread {
        private ByteBuffer buffer = ByteBuffer.allocate(1024);
        private ThreadPool pool;
        private SelectionKey key;

        public WorkerThread(ThreadPool pool) {
            this.pool = pool;
        }

        @Override
        public synchronized void run() {
            System.out.println(this.getName() + " is ready");
            while (true) {
                try {
                    //等待
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    this.interrupt();
                }
                if (key == null) {
                    continue;
                }
                System.out.println(this.getName() + " has been awakened");
                try {
                    //读取信息
                    drainChannel(key);
                } catch (IOException e) {
                    System.out.println("Caught '" + e + "' closing channel");
                    try {
                        key.channel().close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    //立即wakeup，让这条链加入selection
                    key.selector().wakeup();
                }
                key = null;
                this.pool.returnWorker(this);
            }
        }

        void drainChannel(SelectionKey key) throws IOException {
            SocketChannel channel = (SocketChannel) key.channel();
            int count;

            buffer.clear();
            while ((count = channel.read(buffer)) > 0) {
                buffer.flip();
                while (buffer.hasRemaining()) {
                    channel.write(buffer);
                }
                buffer.clear();
            }
            if (count < 0) {
                channel.close();
                return;
            }
            key.interestOps(key.interestOps() | SelectionKey.OP_READ);

            key.selector().wakeup();
        }

        /**
         * 处理数据
         * @param key
         */
        synchronized void serviceChannel(SelectionKey key) {
            //传导key
            this.key = key;
            //修改interestOps防止其他线程再处理这条链的读信息
            key.interestOps(key.interestOps() & (~SelectionKey.OP_READ));
            this.notify();
        }
    }
}
