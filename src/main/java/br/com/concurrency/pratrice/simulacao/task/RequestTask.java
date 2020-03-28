package br.com.concurrency.pratrice.simulacao.task;

import br.com.concurrency.pratrice.domain.entity.Customer;


public class RequestTask implements Runnable{

    private Command advertising;
    private Command email;
    private Command sms;

    public RequestTask(Customer customer){
        this.advertising = new AdvertisingCommand(customer);
        this.email = new EmailCommand(customer);
        this.sms = new SmsCommand(customer);
    }

    @Override
    public void run() {
        System.out.println("Currently Executing thread name - " + Thread.currentThread().getName());
        advertising.run();
        email.run();
        sms.run();
    }
}
