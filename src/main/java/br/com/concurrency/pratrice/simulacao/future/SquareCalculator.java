package br.com.concurrency.pratrice.simulacao.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SquareCalculator {

    private ExecutorService executor = Executors.newFixedThreadPool(2);

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //example1();
        new SquareCalculator().example2();
    }

    private void example2() throws InterruptedException, ExecutionException {
        var square = new SquareCalculator();
        var future1 = square.calculate(10);
        var future2 = square.calculate(100);

        while(!(future1.isDone() && future2.isDone())) {
            System.out.println(String.format("future1 is %s and future2 is %s", future1.isDone() ? "Done": "not done", future2.isDone() ? "Done": "not done"));
            Thread.sleep(300);
        }

        var result1 = future1.get();
        var result2 = future2.get();

        System.out.println(result1 + " and " + result2);
        executor.shutdown();
    }

    private static void example1() throws InterruptedException, ExecutionException {
        var future = new SquareCalculator().calculate(10);

        while(!future.isDone()){
            System.out.println("Calculando....");
            Thread.sleep(300);
        }

        var result = future.get();

        System.out.println("Resultado " + result);
    }

    public Future<Integer> calculate(Integer input) {
        return executor.submit(() -> {
            Thread.sleep(1000);
            return input * input;
        });
    }
}
