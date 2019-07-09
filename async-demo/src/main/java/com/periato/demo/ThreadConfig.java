package com.periato.demo;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.*;

@EnableAsync
@Configuration
public class ThreadConfig {

    @Bean("taskExecutor")
    public ExecutorService getThread(ThreadFactory threadFactory) {

        ExecutorService executorService = new ThreadPoolExecutor(5, 200, 0L,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(1024), threadFactory,
                new ThreadPoolExecutor.AbortPolicy());

        return executorService;

    }

    @Bean
    public ThreadFactory getThreadFactory() {
        return new ThreadFactoryBuilder().setNameFormat("thread-pool-%d").build();
    }

}
