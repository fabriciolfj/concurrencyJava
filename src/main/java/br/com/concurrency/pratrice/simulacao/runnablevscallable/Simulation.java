package br.com.concurrency.pratrice.simulacao.runnablevscallable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Simulation {

    private static ExecutorService executor = Executors.newSingleThreadExecutor();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FactorialTask task = new FactorialTask(-5);
        Future<Integer> future = executor.submit(task);

        System.out.println(future.get().intValue());

    }
}
