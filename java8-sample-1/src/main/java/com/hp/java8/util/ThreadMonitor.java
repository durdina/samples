package com.hp.java8.util;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.text.MessageFormat;

public class ThreadMonitor {

    public void threadDump() {
        ThreadInfo[] threads = ManagementFactory.getThreadMXBean().dumpAllThreads(true, true);
        for (ThreadInfo info : threads)
            System.out.println(MessageFormat.format("{0} - {1} status={2}", info.getThreadName(), info.getThreadId(),
                                                    info.getThreadState()));
    }

}
