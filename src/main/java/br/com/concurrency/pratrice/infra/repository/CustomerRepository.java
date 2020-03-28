package br.com.concurrency.pratrice.infra.repository;

import br.com.concurrency.pratrice.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
