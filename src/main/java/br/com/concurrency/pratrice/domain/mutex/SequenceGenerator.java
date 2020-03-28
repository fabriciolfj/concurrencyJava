package br.com.concurrency.pratrice.domain.mutex;

public class SequenceGenerator {

    private int currentValue = 0;

    public synchronized int getNextSequence() {
        currentValue = currentValue + 1;
        return currentValue;
    }
}
