package br.com.concurrency.pratrice.simulacao.executors;

import java.util.concurrent.Callable;

public class TaskExample implements Callable<String> {

    private String description;

    public TaskExample(String description) {
        this.description = description;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(10);
        return this.description + " " + Thread.currentThread().getName();
    }
}
