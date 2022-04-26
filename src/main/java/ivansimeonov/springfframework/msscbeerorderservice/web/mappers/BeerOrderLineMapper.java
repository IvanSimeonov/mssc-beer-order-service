package ivansimeonov.springfframework.msscbeerorderservice.web.mappers;

import ivansimeonov.springfframework.msscbeerorderservice.domain.BeerOrderLine;
import ivansimeonov.springfframework.msscbeerorderservice.web.model.BeerOrderLineDto;
import org.mapstruct.Mapper;

/**
 * @Author ivansimeonov
 * @Date 26.04.22
 */
@Mapper(uses = {DateMapper.class})
public interface BeerOrderLineMapper {
    BeerOrderLineDto beerOrderLineToBeerOrderLineDto(BeerOrderLine beerOrderLine);

    BeerOrderLine beerOrderLineDtoToBeerOrderLine(BeerOrderLineDto beerOrderLineDto);
}
