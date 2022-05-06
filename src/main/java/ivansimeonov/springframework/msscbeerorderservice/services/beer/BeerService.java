package ivansimeonov.springframework.msscbeerorderservice.services.beer;

import ivansimeonov.springframework.msscbeerorderservice.services.beer.model.BeerDto;

import java.util.Optional;
import java.util.UUID;

/**
 * @Author ivansimeonov
 * @Date 6.05.22
 */
public interface BeerService {
    Optional<BeerDto> getBeerById(UUID beerId);

    Optional<BeerDto> getBeerByUpc(String upc);
}
