package edu.youzg.util;

/**
 * 线程状态 查看器
 */
public class ThreadLooker {

    public ThreadLooker() {
    }

    /**
     * 查询 当前还在运行的线程 及其 状态
     * @return 当前还在运行的线程 及其 状态
     */
    public synchronized static String lookThreads() {
        ThreadGroup thGroup = Thread.currentThread().getThreadGroup().getParent();

        Thread[] threads = new Thread[thGroup.activeCount()];
        thGroup.enumerate(threads);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < threads.length; i++) {
            Thread thread = threads[i];
            result.append("[").append(thread.getId()).append("]")
                    .append(thread.getName()).append(", ")
                    .append(thread.getState()).append("\n");
        }

        return result.toString();
    }

}
