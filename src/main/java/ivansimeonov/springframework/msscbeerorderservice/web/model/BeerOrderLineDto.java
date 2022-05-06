package ivansimeonov.springframework.msscbeerorderservice.web.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * @Author ivansimeonov
 * @Date 26.04.22
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BeerOrderLineDto extends BaseItem {

    private UUID beerId;
    private String beerName;
    private String upc;
    private String beerStyle;
    private Integer orderQuantity = 0;
    private BigDecimal price;

    @Builder
    public BeerOrderLineDto(UUID id, Integer version, OffsetDateTime createdDate, OffsetDateTime lastModifiedDate,
                            UUID beerId, String beerName, String upc, String beerStyle, Integer orderQuantity, BigDecimal price) {
        super(id, version, createdDate, lastModifiedDate);
        this.beerId = beerId;
        this.beerName = beerName;
        this.upc = upc;
        this.beerStyle = beerStyle;
        this.orderQuantity = orderQuantity;
        this.price = price;
    }

}
