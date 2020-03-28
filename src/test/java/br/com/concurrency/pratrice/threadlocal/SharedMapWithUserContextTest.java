package br.com.concurrency.pratrice.threadlocal;

import br.com.concurrency.pratrice.simulacao.threadlocal.nothreadlocal.SharedMapWithUserContext;
import org.junit.jupiter.api.Test;

public class SharedMapWithUserContextTest {

    @Test
    public void inputData() throws InterruptedException {
        var firstUser = new SharedMapWithUserContext(1L);
        var secondUser = new SharedMapWithUserContext(2L);

        new Thread(firstUser).start();
        new Thread(secondUser).start();

        Thread.sleep(1000);
    }
}
