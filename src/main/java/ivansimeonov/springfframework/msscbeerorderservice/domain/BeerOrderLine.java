package ivansimeonov.springfframework.msscbeerorderservice.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * @Author ivansimeonov
 * @Date 26.04.22
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class BeerOrderLine extends BaseEntity {
    private UUID beerId;
    private Integer orderQuantity = 0;
    private Integer quantityAllocated = 0;

    @ManyToOne
    private BeerOrder beerOrder;

    public BeerOrderLine(UUID id, Long version, Timestamp createdDate, Timestamp lastModifiedDate, BeerOrder beerOrder, UUID beerId, Integer orderQuantity, Integer quantityAllocated) {
        super(id, version, createdDate, lastModifiedDate);
        this.beerId = beerId;
        this.orderQuantity = orderQuantity;
        this.quantityAllocated = quantityAllocated;
        this.beerOrder = beerOrder;
    }
}
