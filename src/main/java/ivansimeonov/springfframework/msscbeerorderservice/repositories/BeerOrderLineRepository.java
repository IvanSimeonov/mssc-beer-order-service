package ivansimeonov.springfframework.msscbeerorderservice.repositories;

import ivansimeonov.springfframework.msscbeerorderservice.domain.BeerOrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @Author ivansimeonov
 * @Date 26.04.22
 */
public interface BeerOrderLineRepository extends JpaRepository<BeerOrderLine, UUID> {
}
