package br.com.concurrency.pratrice.simulacao.future;

import java.util.concurrent.ForkJoinPool;

public class SimulationJoinFork {

    public static void main(String[] args) {
        var forkJoinPool = new ForkJoinPool();
        var calculator = new FactorialSquareCalculator(10);
        forkJoinPool.execute(calculator);
    }
}
