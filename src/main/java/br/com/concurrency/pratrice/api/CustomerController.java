package br.com.concurrency.pratrice.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.concurrency.pratrice.domain.model.mapper.CustomerMapper;
import br.com.concurrency.pratrice.domain.model.vo.CustomerVO;
import br.com.concurrency.pratrice.domain.service.CustomerService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @PostMapping
    public void create(@RequestBody CustomerVO customerVO){
        customerService.save(customerMapper.toCustomer(customerVO));
    }
}
