package ivansimeonov.springfframework.msscbeerorderservice.services;

import ivansimeonov.springfframework.msscbeerorderservice.domain.BeerOrder;
import ivansimeonov.springfframework.msscbeerorderservice.domain.Customer;
import ivansimeonov.springfframework.msscbeerorderservice.domain.OrderStatusEnum;
import ivansimeonov.springfframework.msscbeerorderservice.repositories.BeerOrderRepository;
import ivansimeonov.springfframework.msscbeerorderservice.repositories.CustomerRepository;
import ivansimeonov.springfframework.msscbeerorderservice.web.mappers.BeerOrderMapper;
import ivansimeonov.springfframework.msscbeerorderservice.web.model.BeerOrderDto;
import ivansimeonov.springfframework.msscbeerorderservice.web.model.BeerOrderPagedList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @Author ivansimeonov
 * @Date 27.04.22
 */

@Service
public class BeerOrderServiceImpl implements BeerOrderService {

    private static final String BEER_ORDER_NOT_FOUND_EXCEPTION = "Beer Order Not Found";
    private static final String CUSTOMER_NOT_FOUND_EXCEPTION = "Customer Not Found";

    private final BeerOrderRepository beerOrderRepository;
    private final CustomerRepository customerRepository;
    private final BeerOrderMapper beerOrderMapper;

    public BeerOrderServiceImpl(BeerOrderRepository beerOrderRepository, CustomerRepository customerRepository,
                                BeerOrderMapper beerOrderMapper) {
        this.beerOrderRepository = beerOrderRepository;
        this.customerRepository = customerRepository;
        this.beerOrderMapper = beerOrderMapper;
    }

    @Override
    public BeerOrderPagedList ordersList(UUID customerId, Pageable pageable) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isPresent()) {
            Page<BeerOrder> beerOrders = beerOrderRepository.findAllByCustomer(customer.get(), pageable);

            return new BeerOrderPagedList(beerOrders
                    .stream()
                    .map(beerOrderMapper::beerOrderToBeerOrderDto)
                    .collect(Collectors.toList()), PageRequest.of(
                    beerOrders.getPageable().getPageNumber(),
                    beerOrders.getPageable().getPageSize()),
                    beerOrders.getTotalElements());
        } else {
            return null;
        }
    }

    @Override
    public BeerOrderDto placeOrder(UUID customerId, BeerOrderDto beerOrderDto) {
        Optional<Customer> customer = this.customerRepository.findById(customerId);
        if (customer.isPresent()) {
            BeerOrder beerOrder = this.beerOrderMapper.beerOrderDtoToBeerOrder(beerOrderDto);
            beerOrder.setId(null);
            beerOrder.setCustomer(customer.get());
            beerOrder.setOrderStatus(OrderStatusEnum.NEW);

            beerOrder.getBeerOrderLines().forEach(beerOrderLine -> beerOrderLine.setBeerOrder(beerOrder));

            BeerOrder savedBeerOrder = this.beerOrderRepository.saveAndFlush(beerOrder);

            return this.beerOrderMapper.beerOrderToBeerOrderDto(savedBeerOrder);
        }
        throw new RuntimeException(CUSTOMER_NOT_FOUND_EXCEPTION);
    }

    @Override
    public void pickUpOrder(UUID customerId, UUID orderId) {
        BeerOrder beerOrder = getOrder(customerId, orderId);
        beerOrder.setOrderStatus(OrderStatusEnum.PICKED_UP);
        beerOrderRepository.save(beerOrder);
    }

    @Override
    public BeerOrderDto getOrderById(UUID customerId, UUID orderId) {
        return beerOrderMapper.beerOrderToBeerOrderDto(getOrder(customerId, orderId));
    }

    private BeerOrder getOrder(UUID customerId, UUID orderId) {
        Optional<Customer> customer = this.customerRepository.findById(customerId);
        if (customer.isPresent()) {
            Optional<BeerOrder> beerOrder = this.beerOrderRepository.findById(orderId);
            if (beerOrder.isPresent()) {
                BeerOrder currentBeerOrder = beerOrder.get();
                if (currentBeerOrder.getCustomer().getId().equals(customerId)) {
                    return currentBeerOrder;
                }
            }
            throw new RuntimeException(BEER_ORDER_NOT_FOUND_EXCEPTION);
        }
        throw new RuntimeException(CUSTOMER_NOT_FOUND_EXCEPTION);
    }

}
