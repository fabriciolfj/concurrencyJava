package br.com.concurrency.pratrice.simulacao.killthread;

public class Simulacao {

    public static void main(String[] args) {
        new ControlSubThread(1000).start();
    }
}
