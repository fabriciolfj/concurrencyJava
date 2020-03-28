package br.com.concurrency.pratrice.simulacao.threadlocal.nothreadlocal;

import br.com.concurrency.pratrice.simulacao.threadlocal.Context;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SharedMapWithUserContext implements Runnable {

    public static ThreadLocal<Context> userContext = new ThreadLocal<>();
    private Long userId;
    private UserRepository repository = new UserRepository();

    public SharedMapWithUserContext(Long userId){
        this.userId = userId;
    }

    @Override
    public void run() {
        var userName = repository.getContext(userId);
        userContext.set(new Context(userName));
        System.out.println("thread context for given userId: "
                + userId + " is: " + userContext.get());
    }
}
