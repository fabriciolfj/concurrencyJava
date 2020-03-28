package br.com.concurrency.pratrice.simulacao.threadlocal.nothreadlocal;

import br.com.concurrency.pratrice.simulacao.threadlocal.Context;

import java.util.Arrays;
import java.util.List;

public class UserRepository {

    public List<Context> contexts = Arrays.asList(
            new Context(1L, "Fabricio"),
            new Context(2L, "Suzana"),
            new Context(3L, "Lucas")
    );

    public String getContext(Long id){
        return contexts.stream()
                .filter(f ->  f.getId().equals(id))
                .findFirst()
                .get()
                .getUserName();
    }
}
