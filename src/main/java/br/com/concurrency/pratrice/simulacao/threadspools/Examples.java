package br.com.concurrency.pratrice.simulacao.threadspools;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class Examples {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        new Examples().listeners();
        //new Examples().terminatingVM();
        //new Examples().directExecutor();
        //new Examples().examplesPools();
        //new Examples().exampleSchedulePool2();
    }

    public void listeners() throws ExecutionException, InterruptedException { // para combinar diversos futures
        ExecutorService executorService = Executors.newCachedThreadPool();
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(executorService);

        ListenableFuture<String> future1 = listeningExecutorService.submit(() -> "Hello");
        ListenableFuture<String> future2 = listeningExecutorService.submit(() -> "world");

        String result = Futures.allAsList(future1, future2).get()
                .stream()
                .collect(Collectors.joining(" "));

        System.out.println(result);
    }

    public void terminatingVM() { // ao desligar a vm, ele encerra as threads em execução junto.
        ThreadPoolExecutor executor =
                (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        ExecutorService executorService =
                MoreExecutors.getExitingExecutorService(executor,
                        100, TimeUnit.MILLISECONDS);

        executorService.submit(() -> {
            while (true) {
            }
        });
    }

    public void directExecutor(){ // usando a lib guava, executar na thread principal
        Executor executor = MoreExecutors.directExecutor();
        AtomicBoolean executed = new AtomicBoolean();

        executor.execute(() -> {
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            executed.set(true);
        });

        System.out.println(executed.get());

    }

    public void exampleSchedulePool2() throws InterruptedException { //repetir 3 vezes a cada 100 mil, com atraso de 500 mil
        CountDownLatch lock =  new CountDownLatch(3);
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);
        ScheduledFuture<?> future = executor.scheduleAtFixedRate(() -> {
            System.out.println("Hello worl");
            lock.countDown();
        }, 500, 100, TimeUnit.MILLISECONDS);

        lock.await(1000, TimeUnit.MILLISECONDS); // esperar 1000 mil para executar 3 vezes
        future.cancel(true);

    }

    public void examplesScheduledPool(){ // com atraso em 500 milisegundos
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);
        executor.schedule(() -> {
            System.out.println("Hello world");
        }, 500, TimeUnit.MILLISECONDS);
    }

    public void examplesPools(){
        //ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        executor.submit(() -> {
                Thread.sleep(1000);
                return null;
        });

        executor.submit(() -> {
            Thread.sleep(1000);
            return null;
        });

        executor.submit(() -> {
            Thread.sleep(1000);
            return null;
        });

        System.out.println(executor.getPoolSize());
        System.out.println(executor.getQueue().size());
    }
}
