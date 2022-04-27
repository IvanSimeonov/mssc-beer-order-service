package ivansimeonov.springframework.msscbeerorderservice.repositories;

import ivansimeonov.springframework.msscbeerorderservice.domain.BeerOrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @Author ivansimeonov
 * @Date 26.04.22
 */
@Repository
public interface BeerOrderLineRepository extends JpaRepository<BeerOrderLine, UUID> {
}
