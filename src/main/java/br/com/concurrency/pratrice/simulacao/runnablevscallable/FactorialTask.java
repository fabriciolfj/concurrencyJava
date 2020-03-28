package br.com.concurrency.pratrice.simulacao.runnablevscallable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.security.InvalidParameterException;
import java.util.concurrent.Callable;

@Getter
@Setter
@AllArgsConstructor
public class FactorialTask implements Callable<Integer> {

    private Integer number;

    @Override
    public Integer call() throws InvalidParameterException, InterruptedException {
        test();
        if(number < 0)
            throw new InvalidParameterException("number invalid.");

        return 0;
    }

    private synchronized void test() throws InterruptedException {
        int value = 0;
        while(value == 0){
            wait();
        }

        value = 1;
    }
}
