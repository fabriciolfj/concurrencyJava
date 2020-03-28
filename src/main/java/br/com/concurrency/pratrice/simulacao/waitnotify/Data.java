package br.com.concurrency.pratrice.simulacao.waitnotify;

public class Data {

    private String packet;
    private boolean transfer = true;

    public synchronized void send(String packet) {
        while(!transfer) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrupted, " + e);
            }
        }

        this.packet = packet;
        transfer = false;
        notifyAll();
    }

    public synchronized String receive() {
        while (transfer) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrupted, " + e);
            }
        }

        transfer = true;
        notifyAll();
        return packet;
    }
}
