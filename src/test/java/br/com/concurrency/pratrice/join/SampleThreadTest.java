package br.com.concurrency.pratrice.join;

import br.com.concurrency.pratrice.simulacao.join.SampleThread;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class SampleThreadTest {

    private static Logger LOGGER = LoggerFactory.getLogger(SampleThread.class);

    @Test
    public void givenStartThread_whenJoinCalled_waitsTillCompletion() throws InterruptedException {
        Thread t2 = new SampleThread(10);
        t2.start();
        LOGGER.info("invoking join");
        t2.join(100);
        LOGGER.info("Returned from join");
        assertFalse(t2.isAlive());

    }
}
