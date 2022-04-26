package ivansimeonov.springfframework.msscbeerorderservice.repositories;

import ivansimeonov.springfframework.msscbeerorderservice.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * @Author ivansimeonov
 * @Date 26.04.22
 */
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    List<Customer> findAllByCustomerNameLike(String customerName);
}
