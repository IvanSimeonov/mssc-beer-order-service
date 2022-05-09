package ivansimeonov.springframework.msscbeerorderservice.bootstrap;

import ivansimeonov.springframework.msscbeerorderservice.domain.Customer;
import ivansimeonov.springframework.msscbeerorderservice.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @Author ivansimeonov
 * @Date 6.05.22
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class BeerOrderBootstrap implements CommandLineRunner {
    public static final String TASTING_ROOM = "Tasting Room";
    public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234300019";
    public static final String BEER_3_UPC = "0083783375213";

    private final CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {
        loadCustomerData();
    }

    private void loadCustomerData() {
        if (customerRepository.findAllByCustomerNameLike(BeerOrderBootstrap.TASTING_ROOM) .size() == 0) {
            Customer savedCustomer = customerRepository.saveAndFlush(Customer.builder()
                    .customerName(TASTING_ROOM)
                    .apiKey(UUID.randomUUID())
                    .build());

            log.warn("Tasting Room Customer Id: " + savedCustomer.getId().toString());
        }
    }
}