package br.com.concurrency.pratrice.simulacao.threadlocal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Context {

    private Long id;
    private String userName;

    public Context(String userName){
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Context{" +
                "userName='" + userName + '\'' +
                '}';
    }
}
