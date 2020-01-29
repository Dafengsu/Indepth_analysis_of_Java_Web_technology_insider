package Demo.network.thread;

import java.util.concurrent.Callable;

/**
 * @description:
 * @author: su
 * @date: 2020/1/28
 */
public class FindMaxTask implements Callable<Integer> {
    private int[] data;
    private int start;
    private int end;

    public FindMaxTask(int[] data, int start, int end) {
        this.data = data;
        this.start = start;
        this.end = end;
    }

    @Override
    public Integer call() {
        int max = Integer.MIN_VALUE;

        for (int i = start; i < end; i++)
            if (data[i] > max) max = data[i];

        return max;
    }
}
