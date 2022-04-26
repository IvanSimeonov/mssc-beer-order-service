package ivansimeonov.springfframework.msscbeerorderservice.services;

import ivansimeonov.springfframework.msscbeerorderservice.web.model.BeerOrderDto;
import ivansimeonov.springfframework.msscbeerorderservice.web.model.BeerOrderPagedList;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

/**
 * @Author ivansimeonov
 * @Date 26.04.22
 */
public interface BeerOrderService {

    BeerOrderPagedList ordersList(UUID customerId, Pageable pageable);

    BeerOrderDto placeOrder(UUID customerId, BeerOrderDto beerOrderDto);

    BeerOrderDto getOrderById(UUID customerId,UUID orderId);

    void pickUpOrder(UUID customerId, UUID orderId);
}
