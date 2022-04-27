package ivansimeonov.springfframework.msscbeerorderservice.web.mappers;

import ivansimeonov.springfframework.msscbeerorderservice.domain.BeerOrderLine;
import ivansimeonov.springfframework.msscbeerorderservice.web.model.BeerOrderLineDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * @Author ivansimeonov
 * @Date 26.04.22
 */
@Component
@Mapper(uses = {DateMapper.class})
public interface BeerOrderLineMapper {
    BeerOrderLineDto beerOrderLineToBeerOrderLineDto(BeerOrderLine beerOrderLine);

    BeerOrderLine beerOrderLineDtoToBeerOrderLine(BeerOrderLineDto beerOrderLineDto);
}
