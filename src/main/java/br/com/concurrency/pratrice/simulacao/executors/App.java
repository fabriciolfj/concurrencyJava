package br.com.concurrency.pratrice.simulacao.executors;

import br.com.concurrency.pratrice.domain.entity.Customer;
import br.com.concurrency.pratrice.simulacao.task.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class App {

    private static ThreadPoolExecutor executor;
    private static List<Command> commands = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
        System.out.println("Application initialization complete.");
        var customers = getCustomers();
        var index = 0;
        while(index < customers.size()) {
            try {
                var request = new RequestTask(customers.get(index));
                executor.execute(request);
            } catch (Exception e) {
                e.printStackTrace();
            }

            index++;
        }

        executor.awaitTermination(30, TimeUnit.SECONDS);
        Logger.initializeLog();
        System.out.println("Shutting down");
        executor.shutdown();
        Logger.shutdown();
    }

    private static List<Customer> getCustomers() {
        return Arrays.asList(
                new Customer(1L, "Fabricio"),
                new Customer(2L, "Suzana"),
                new Customer(3L, "Carlos"),
                new Customer(4L, "Sebastião")
        );
    }
}
