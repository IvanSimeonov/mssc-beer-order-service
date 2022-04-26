package ivansimeonov.springfframework.msscbeerorderservice.repositories;

import ivansimeonov.springfframework.msscbeerorderservice.domain.BeerOrder;
import ivansimeonov.springfframework.msscbeerorderservice.domain.Customer;
import ivansimeonov.springfframework.msscbeerorderservice.domain.OrderStatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.awt.print.Pageable;
import java.util.List;
import java.util.UUID;

/**
 * @Author ivansimeonov
 * @Date 26.04.22
 */
public interface BeerOrderRepository extends JpaRepository<BeerOrder, UUID> {
    Page<BeerOrder> findAllByCustomer(Customer customer, Pageable pageable);

    List<BeerOrder> findAllByOrderStatus(OrderStatusEnum orderStatus);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    BeerOrder findOneById(UUID id);
}
