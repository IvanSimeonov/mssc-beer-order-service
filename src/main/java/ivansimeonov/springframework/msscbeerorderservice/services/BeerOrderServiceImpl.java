package ivansimeonov.springframework.msscbeerorderservice.services;

import ivansimeonov.springframework.msscbeerorderservice.domain.BeerOrder;
import ivansimeonov.springframework.msscbeerorderservice.domain.Customer;
import ivansimeonov.springframework.msscbeerorderservice.domain.OrderStatusEnum;
import ivansimeonov.springframework.msscbeerorderservice.repositories.BeerOrderRepository;
import ivansimeonov.springframework.msscbeerorderservice.repositories.CustomerRepository;
import ivansimeonov.springframework.msscbeerorderservice.web.mappers.BeerOrderMapper;
import ivansimeonov.springframework.msscbeerorderservice.web.model.BeerOrderDto;
import ivansimeonov.springframework.msscbeerorderservice.web.model.BeerOrderPagedList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

@Slf4j
@Service
public class BeerOrderServiceImpl implements BeerOrderService {

    private static final String BEER_ORDER_NOT_FOUND_EXCEPTION = "Beer Order Not Found";
    private static final String CUSTOMER_NOT_FOUND_EXCEPTION = "Customer Not Found";

    @Autowired
    private final BeerOrderRepository beerOrderRepository;

    @Autowired
    private final CustomerRepository customerRepository;

    @Autowired
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
        Optional<Customer> customer = customerRepository.findById(customerId);
        log.warn("Customer: " + (customer.isPresent() ? customer.get().getCustomerName() : "none"));
        if (customer.isPresent()) {
            BeerOrder beerOrder = beerOrderMapper.beerOrderDtoToBeerOrder(beerOrderDto);
            beerOrder.setId(null);
            beerOrder.setCustomer(customer.get());
            beerOrder.setOrderStatus(OrderStatusEnum.NEW);

            beerOrder.getBeerOrderLines().forEach(beerOrderLine -> beerOrderLine.setBeerOrder(beerOrder));

            BeerOrder savedBeerOrder = beerOrderRepository.saveAndFlush(beerOrder);

            log.warn("Saved Beer Order: " + beerOrder.getId());

            return beerOrderMapper.beerOrderToBeerOrderDto(savedBeerOrder);
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
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isPresent()) {
            Optional<BeerOrder> beerOrder = beerOrderRepository.findById(orderId);
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
