package ivansimeonov.springframework.msscbeerorderservice.web.model;

import ivansimeonov.springframework.msscbeerorderservice.domain.OrderStatusEnum;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @Author ivansimeonov
 * @Date 26.04.22
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BeerOrderDto extends BaseItem {

    private UUID customerId;
    private String customerRef;
    private List<BeerOrderLineDto> beerOrderLines;
    private OrderStatusEnum orderStatus;
    private String orderStatusCallbackUrl;

    @Builder
    public BeerOrderDto(UUID id, Integer version, OffsetDateTime createdDate, OffsetDateTime lastModifiedDate,
                        UUID customerId, List<BeerOrderLineDto> beerOrderLines, OrderStatusEnum orderStatus,
                        String orderStatusCallbackUrl, String customerRef) {
        super(id, version, createdDate, lastModifiedDate);
        this.customerId = customerId;
        this.customerRef = customerRef;
        this.beerOrderLines = beerOrderLines;
        this.orderStatus = orderStatus;
        this.orderStatusCallbackUrl = orderStatusCallbackUrl;
    }
}
