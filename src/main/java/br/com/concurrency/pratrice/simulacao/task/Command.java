package br.com.concurrency.pratrice.simulacao.task;

import br.com.concurrency.pratrice.domain.entity.Customer;

public abstract class Command implements Runnable{

    private final Customer customer;

    public Command(Customer customer){
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }
}
