package br.com.concurrency.pratrice.domain.model.mapper;

import br.com.concurrency.pratrice.domain.entity.Customer;
import br.com.concurrency.pratrice.domain.model.vo.CustomerVO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-03-09T23:05:35-0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 13.0.2 (Oracle Corporation)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public Customer toCustomer(CustomerVO customerVO) {
        if ( customerVO == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setName( customerVO.getName() );

        return customer;
    }
}
