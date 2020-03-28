package br.com.concurrency.pratrice.simulacao.task;

import java.util.concurrent.TimeUnit;

public class LogTask implements Runnable {
    @Override
    public void run() {
        while(Thread.currentThread().isInterrupted()){
            try {
                TimeUnit.SECONDS.sleep(10);
                Logger.writeLogs();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Logger.writeLogs();
    }
}
