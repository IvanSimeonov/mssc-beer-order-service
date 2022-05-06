package ivansimeonov.springframework.msscbeerorderservice.web.mappers;

import ivansimeonov.springframework.msscbeerorderservice.domain.BeerOrder;
import ivansimeonov.springframework.msscbeerorderservice.web.model.BeerOrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

/**
 * @Author ivansimeonov
 * @Date 26.04.22
 */
@Component
@Mapper(uses = {DateMapper.class, BeerOrderLineMapper.class}, componentModel = "spring")
public interface BeerOrderMapper {
    @Mapping(target = "customerId", source = "customer.id")
    BeerOrderDto beerOrderToBeerOrderDto(BeerOrder beerOrder);

    BeerOrder beerOrderDtoToBeerOrder(BeerOrderDto beerOrderDto);
}
