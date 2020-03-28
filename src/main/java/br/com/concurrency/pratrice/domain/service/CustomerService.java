package br.com.concurrency.pratrice.domain.service;

import br.com.concurrency.pratrice.infra.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.com.concurrency.pratrice.domain.entity.Customer;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Async("executorsAsync")
    public void save(Customer customer){
        System.out.println("Currently Executing thread name - " + Thread.currentThread().getName());
        System.out.println(customer);
        System.out.println("User created with thread pool executor");
    }



}
