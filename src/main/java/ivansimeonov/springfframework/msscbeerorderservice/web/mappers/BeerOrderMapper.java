package ivansimeonov.springfframework.msscbeerorderservice.web.mappers;

import ivansimeonov.springfframework.msscbeerorderservice.domain.BeerOrder;
import ivansimeonov.springfframework.msscbeerorderservice.web.model.BeerOrderDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * @Author ivansimeonov
 * @Date 26.04.22
 */
@Component
@Mapper(uses = {DateMapper.class, BeerOrderLineMapper.class})
public interface BeerOrderMapper {
    BeerOrderDto beerOrderToBeerOrderDto(BeerOrder beerOrder);

    BeerOrder beerOrderDtoToBeerOrder(BeerOrderDto beerOrderDto);
}
