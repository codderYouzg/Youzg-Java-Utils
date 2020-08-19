package edu.youzg.util;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 滴答滴答定时器<br/>
 * 自定义定时器<br/>
 * 优化：Lock锁 及 Condition 的使用
 */
public abstract class Didadida implements Runnable {
    private static final long DEFAULT_DELAY_TIME = 1000;    // 默认延迟时间

    private volatile boolean goon;  // 用于 开启/关闭线程
    private long delay; // 延迟时间
    private Lock lock;  // 安全“锁”对象
    private Condition condition;    // 用于控制 当前线程 的 状态

    public Didadida() {
        this(DEFAULT_DELAY_TIME);
    }

    public Didadida(long delay) {
        this.delay = delay;
        this.lock = new ReentrantLock();
        this.condition = lock.newCondition();
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }

    public void start() {
        if (this.goon) {
            return;
        }
        this.goon = true;
        new Thread(this).start();
    }

    public void stop() {
        if (!this.goon) {
            return;
        }
        this.goon = false;
    }

    protected abstract void doTask();

    @Override
    public void run() {
        while (this.goon) {
            lock.lock();
            try {
                // await() 方法释放对象锁
                // 参1 ———— 大小
                // 参2 ———— 单位(此处设置的单位是 毫秒)
                this.condition.await(this.delay, TimeUnit.MILLISECONDS);    // 1. 被唤醒 2. 超过设定的时间 3. 阻塞状态
                //TimeUnit.MILLISECONDS.sleep(delay);
                new InnerWorker();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    private class InnerWorker implements Runnable {
        public InnerWorker() {
            new Thread(this).start();
        }

        @Override
        public void run() {
            doTask();
        }
    }

}
