package ivansimeonov.springfframework.msscbeerorderservice.web.mappers;

import ivansimeonov.springfframework.msscbeerorderservice.domain.BeerOrder;
import ivansimeonov.springfframework.msscbeerorderservice.web.model.BeerOrderDto;
import org.mapstruct.Mapper;

/**
 * @Author ivansimeonov
 * @Date 26.04.22
 */
@Mapper(uses = {DateMapper.class, BeerOrderLineMapper.class})
public interface BeerOrderMapper {
    BeerOrderDto beerOrderToBeerOrderDto(BeerOrder beerOrder);

    BeerOrder beerOrderDtoToBeerOrder(BeerOrderDto beerOrderDto);
}
