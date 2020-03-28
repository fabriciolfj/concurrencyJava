package br.com.concurrency.pratrice.simulacao.executors;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class AppTwo {

    private ExecutorService executor = Executors.newFixedThreadPool(10);
    private Random random = new Random();

    public static void main(String[] args) {
        new AppTwo().taks();
    }

    public void taks(){
        Callable<Integer> task1 = () -> processar();
        Callable<Integer> task2 = () -> processar();

        Future<Integer> future1 = executor.submit(task2);
        Future<Integer> future2 = executor.submit(task1);

        try {
            Integer value1 = future1.get(200, TimeUnit.MILLISECONDS);
            System.out.println(value1);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }

        try {
            Integer value2 = future2.get(200, TimeUnit.MILLISECONDS);
            System.out.println(value2);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }

    }

    private Integer processar() throws InterruptedException {
        Thread.sleep(300);
        return random.nextInt() * random.nextInt();
    }
}

