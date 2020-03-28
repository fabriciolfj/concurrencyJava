package br.com.concurrency.pratrice.simulacao.executors;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class AppThree {

    private ExecutorService executor = Executors.newFixedThreadPool(10);
    private CompletionService<String> service = new ExecutorCompletionService<>(executor);

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        new AppThree().processCompletation();
    }

    //invokeall -> retorna na mesma ordem da lista, ja o completionService retorna conforme as tarefas são finalizadas.
    public void processAll() throws InterruptedException {
        Callable<String> task1 = new TaskExample("Example 1");
        Callable<String> task2 = new TaskExample("Example 2");
        Callable<String> task3 = new TaskExample("Example 3");

        List<Callable<String>> list = Arrays.asList(task1, task2, task3);
        executor.invokeAll(list).stream().filter(result -> result.isDone())
                .map(future -> {
                    try {
                        return future.get();
                    } catch (InterruptedException | ExecutionException e) {
                        throw new RuntimeException("Fail execute");
                    }
                }).forEach(System.out::println);
    }

    public void processCompletation() throws InterruptedException, ExecutionException {
        Callable<String> task1 = new TaskExample("Example 1");
        Callable<String> task2 = new TaskExample("Example 2");
        Callable<String> task3 = new TaskExample("Example 3");

        service.submit(task1);
        service.submit(task2);
        service.submit(task3);

        CountDownLatch countDownLatch = new CountDownLatch(4);

        while(countDownLatch.getCount() > 0){
            System.out.println(service.take().get());

            countDownLatch.countDown();
        }

        countDownLatch.await();
    }

    public void process() {
        Callable<String> task1 = new TaskExample("Example 1");
        Callable<String> task2 = new TaskExample("Example 2");
        Callable<String> task3 = new TaskExample("Example 3");

        Future<String> result1 = this.executor.submit(task1);
        Future<String> result2 = this.executor.submit(task2);
        Future<String> result3 = this.executor.submit(task3);

        try{

            executor.shutdown();

            if(!executor.awaitTermination(5, TimeUnit.MILLISECONDS)){
                executor.shutdownNow();
            }

        } catch (Exception e)  {
            e.printStackTrace();
        }

        try {
            while(true) {

                if(result1.isDone() && result2.isDone() && result3.isDone()){
                    System.out.println(result1.get());
                    System.out.println(result2.get());
                    System.out.println(result3.get());
                    return;
                }

            }
        } catch (Exception e ){
            e.printStackTrace();
        }


    }
}
