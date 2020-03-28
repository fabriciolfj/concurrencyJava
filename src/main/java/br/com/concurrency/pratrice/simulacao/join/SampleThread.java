package br.com.concurrency.pratrice.simulacao.join;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SampleThread extends Thread {

    private static Logger LOGGER = LoggerFactory.getLogger(SampleThread.class);

    public int processingCount = 0;

    public SampleThread(int processingCount) {
        this.processingCount = processingCount;
        LOGGER.info("Thread Created");
    }

    @Override
    public void run() {
        LOGGER.info("Thread " + this.getName() + " started");
        while (processingCount > 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                LOGGER.info("Thread " + this.getName() + " interrupted");
            }
            processingCount--;
        }
        LOGGER.info("Thread " + this.getName() + " exiting");
    }
}
