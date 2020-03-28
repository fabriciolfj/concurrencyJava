package br.com.concurrency.pratrice.simulacao.killthread;

import java.util.concurrent.atomic.AtomicBoolean;

public class ControlSubThread implements Runnable {

    private Thread worker;
    private final AtomicBoolean running = new AtomicBoolean(false);
    private final AtomicBoolean stopped= new AtomicBoolean(true);
    private int interval;

    public ControlSubThread(int interval) {
        this.interval = interval;
    }

    public void start(){
        worker = new Thread(this);
        worker.start();
    }

    @Override
    public void run() {
        running.set(true);
        stopped.set(false);
        while(running.get()){
            try{
                Thread.sleep(this.interval);
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
                System.out.println("Thread was interrupted, failed to complete operation.");
            }
        }
        stopped.set(true);
    }
}
