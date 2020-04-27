package br.com.concurrency.pratrice.simulacao.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Simulacao {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new Simulacao().test();
    }

    // thenCompose(usa o estágio anterior como argumento. Ele achatará e retornará um futuro diretamente com o resultado, em vez de um futuro aninhado, como observamos em thenApply ()) vs themApply (é útil quando queremos transformar o resultado de uma chamada )
    public void test() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> "hello")
                .thenCompose(s -> CompletableFuture.supplyAsync(() ->s + "world"));

        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(() -> "hello")
                .thenApply(s -> s + "world");

        String value = completableFuture1.get();
        String value2 = completableFuture2.get();

        System.out.println(value);
        System.out.println(value2);
    }

    public CompletableFuture<String> tratandoError() {
        String name = null;

        CompletableFuture<String> completableFuture
                =  CompletableFuture.supplyAsync(() -> {
            if (name == null) {
                throw new RuntimeException("Computation error!");
            }
            return "Hello, " + name;
        }).handle((s, t) -> s != null ? s : "Hello, Stranger!");

        return completableFuture;
    }

    //uso por traz de um fork join
    public void forkJoin() {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "hello");
        Future<String> future = completableFuture.thenApplyAsync( s-> s + "world");
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void variosCompletableFuture() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> "hello");
        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(() -> "world");
        CompletableFuture<Void> completableFuture = CompletableFuture.allOf(completableFuture1, completableFuture2);

        completableFuture.get();
        String combinado = Stream.of(completableFuture1, completableFuture2)
                .map(CompletableFuture::join)
                .collect(Collectors.joining(" "));

        System.out.println(combinado);
    }

    //combinando 2 completablefuture
    public Future<String> combinacao() {
        /*CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "hello")
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " world"));*/

        //ou
        /*CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "hello")
                .thenCombine(CompletableFuture.supplyAsync(() -> " world"), (s1, s2) -> s1 + s2);*/

        //ou quando não preciso passar o valor resultando na cadeia
        CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> "hello")
                .thenAcceptBoth(CompletableFuture.supplyAsync(() -> " world"), (s1, s2) -> System.out.println(s1 + s2));

        return null;
    }

    // se não preciso do valor nem para retornar nem para consumir, uso o run
    public Future<Void> exampleFive() {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "hello");
        CompletableFuture<Void> process = completableFuture.thenRun(() -> System.out.println("Finished"));
        return process;
    }

    //quando não preciso retornar um valor, uso o thenAccept e passo um consumidor.
    public Future<Void> exampleFour() {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "hello");
        CompletableFuture<Void> process = completableFuture.thenAccept(s -> System.out.println(s + " world"));
        return process;
    }

    // para processar uma função de forma assincrona
    public Future<String> exampleThree() {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "hello");
        
        //thenApply pega o retorno e usa em uma função
        CompletableFuture<String> process = completableFuture.thenApply(s -> s + " world");
        return process;
    }

    // supplyAsync retorna um valor, o mesmo que específicado
    public Future<String> exampleTwo() {
        return CompletableFuture.supplyAsync(() ->  "hello");
    }

    public Future<String> exampleOne() {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(() -> {
            Thread.sleep(500);
            completableFuture.complete("Hello");
            return null;
        });

        return completableFuture;
    }
}
