package chapter02;




import com.sun.xml.internal.ws.api.message.Message;
import com.sun.xml.internal.ws.api.message.MessageHeaders;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: su
 * @date: 2020/2/11
 */
public class AsyncResult {
    private byte[] result;
    private AtomicBoolean done = new AtomicBoolean(false);
    private Lock lock = new ReentrantLock();
    private Condition condition;
    private long startTime;

    public AsyncResult() {
        condition = lock.newCondition();
        startTime = System.currentTimeMillis();
    }

    public byte[] get() {
        lock.lock();
        try {
            if (!done.get()) {
                condition.await();
            }
        } catch (InterruptedException e) {
            throw new AssertionError();
        }
        lock.unlock();
        return result;
    }

    public boolean isDone() {
        return done.get();
    }

    public byte[] get(long timeout, TimeUnit tu) throws TimeoutException {
        lock.lock();
        try {
            boolean bVal = true;
            try {
                if (!done.get()) {
                    long overall_timeout = timeout - (System.currentTimeMillis() - startTime);
                    if (overall_timeout > 0) {
                        bVal = condition.await(overall_timeout, TimeUnit.MILLISECONDS);
                    } else {
                        bVal = false;
                    }
                }
            } catch (InterruptedException e) {
                throw new AssertionError();
            }
            if (!bVal && !done.get()) {
                throw new TimeoutException("Operation timed out");
            }
        }finally {
            lock.unlock();
        }
        return result;
    }

    public void result(Message response) {
        try {
            lock.lock();
            if (!done.get()) {
                result = response.getHeaders().toString().getBytes();
                done.set(true);
                condition.signal();
            }
        }finally {
            lock.unlock();
        }
    }
}
