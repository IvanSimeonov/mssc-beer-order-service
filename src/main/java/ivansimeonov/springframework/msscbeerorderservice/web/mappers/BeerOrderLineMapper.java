package ivansimeonov.springframework.msscbeerorderservice.web.mappers;

import ivansimeonov.springframework.msscbeerorderservice.domain.BeerOrderLine;
import ivansimeonov.springframework.msscbeerorderservice.web.model.BeerOrderLineDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * @Author ivansimeonov
 * @Date 26.04.22
 */
@Component
@Mapper(uses = {DateMapper.class}, componentModel = "spring")
@DecoratedWith(BeerOrderLineMapperDecorator.class)
public interface BeerOrderLineMapper {
    BeerOrderLineDto beerOrderLineToBeerOrderLineDto(BeerOrderLine beerOrderLine);

    BeerOrderLine beerOrderLineDtoToBeerOrderLine(BeerOrderLineDto beerOrderLineDto);
}
