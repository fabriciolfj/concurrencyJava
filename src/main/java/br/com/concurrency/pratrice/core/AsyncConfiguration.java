package br.com.concurrency.pratrice.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class AsyncConfiguration {

    @Bean(name = "executorsAsync")
    public ThreadPoolExecutor threadPoolExecutor(){
        var numThreads  = Runtime.getRuntime().availableProcessors() * 2;
        var pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(numThreads);
        return pool;
    }
}
