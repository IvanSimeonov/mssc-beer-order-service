package ivansimeonov.springfframework.msscbeerorderservice.web.controllers;

import ivansimeonov.springfframework.msscbeerorderservice.services.BeerOrderService;
import ivansimeonov.springfframework.msscbeerorderservice.web.model.BeerOrderDto;
import ivansimeonov.springfframework.msscbeerorderservice.web.model.BeerOrderPagedList;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @Author ivansimeonov
 * @Date 27.04.22
 */
@RestController
@RequestMapping("/api/v1/customers/{customerId}/")
public class BeerOrderController {

    private final static Integer DEFAULT_PAGE_NUMBER = 0;
    private final static Integer DEFAULT_PAGE_SIZE = 25;

    private final BeerOrderService beerOrderService;

    public BeerOrderController(BeerOrderService beerOrderService) {
        this.beerOrderService = beerOrderService;
    }

    @GetMapping("orders")
    public BeerOrderPagedList ordersList(@PathVariable("customerId") UUID customerId,
                                         @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                         @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        if (pageNumber == null || pageNumber < 0) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }

        if (pageSize == null || pageSize < 0) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        return beerOrderService.ordersList(customerId, PageRequest.of(pageNumber, pageSize));
    }

    @GetMapping("orders/{orderId}")
    public BeerOrderDto getOrderById(@PathVariable("customerId") UUID customerId, @PathVariable("orderId") UUID orderId) {
        return beerOrderService.getOrderById(customerId, orderId);
    }

    @PostMapping("orders")
    @ResponseStatus(HttpStatus.CREATED)
    public BeerOrderDto placeOrder(@PathVariable("customerId") UUID customerId, @RequestBody BeerOrderDto beerOrderDto) {
        return beerOrderService.placeOrder(customerId, beerOrderDto);
    }

    @PutMapping("orders/{orderId}/pickup")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void pickUpOrder(@PathVariable("customerId") UUID customerId, @PathVariable("orderId") UUID orderId) {
        beerOrderService.pickUpOrder(customerId, orderId);
    }

}
