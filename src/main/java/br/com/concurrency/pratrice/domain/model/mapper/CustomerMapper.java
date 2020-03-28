package br.com.concurrency.pratrice.domain.model.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import br.com.concurrency.pratrice.domain.entity.Customer;
import br.com.concurrency.pratrice.domain.model.vo.CustomerVO;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "name")
    Customer toCustomer(CustomerVO customerVO);
}
