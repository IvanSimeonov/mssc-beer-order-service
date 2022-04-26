package ivansimeonov.springfframework.msscbeerorderservice.web.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * @Author ivansimeonov
 * @Date 26.04.22
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrderStatusUpdate extends BaseItem {

    private UUID orderId;
    private String customerRef;
    private String orderStatus;

    @Builder
    public OrderStatusUpdate(UUID id, Integer version, OffsetDateTime createdDate, OffsetDateTime lastModifiedDate,
                             UUID orderId, String customerRef, String orderStatus) {
        super(id, version, createdDate, lastModifiedDate);
        this.customerRef = customerRef;
        this.orderStatus = orderStatus;
    }
}
