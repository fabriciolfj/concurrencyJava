package br.com.concurrency.pratrice.simulacao.task;

import br.com.concurrency.pratrice.domain.entity.Customer;

public class SmsCommand extends Command implements Runnable {

    public SmsCommand(Customer customer) {
        super(customer);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println("Currently Executing thread name - " + Thread.currentThread().getName());
            Logger.sendMessage(String.format("Send sms to customer %s", getCustomer().getName()));
            System.out.println(String.format("Send sms to customer %s", getCustomer().getName()));

            System.out.println("===========================================================");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
