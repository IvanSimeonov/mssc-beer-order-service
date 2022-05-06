package ivansimeonov.springframework.msscbeerorderservice.web.mappers;

import ivansimeonov.springframework.msscbeerorderservice.domain.BeerOrderLine;
import ivansimeonov.springframework.msscbeerorderservice.services.beer.BeerService;
import ivansimeonov.springframework.msscbeerorderservice.services.beer.model.BeerDto;
import ivansimeonov.springframework.msscbeerorderservice.web.model.BeerOrderLineDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Optional;

/**
 * @Author ivansimeonov
 * @Date 6.05.22
 */
public abstract class BeerOrderLineMapperDecorator implements BeerOrderLineMapper {
    private BeerService beerService;
    private BeerOrderLineMapper beerOrderLineMapper;

    @Autowired
    public void setBeerService(BeerService beerService) {
        this.beerService = beerService;
    }

    @Autowired
    @Qualifier("delegate")
    public void setBeerOrderLineMapper(BeerOrderLineMapper beerOrderLineMapper) {
        this.beerOrderLineMapper = beerOrderLineMapper;
    }

    @Override
    public BeerOrderLineDto beerOrderLineToBeerOrderLineDto(BeerOrderLine beerOrderLine) {
        BeerOrderLineDto beerOrderLineDto = beerOrderLineMapper.beerOrderLineToBeerOrderLineDto(beerOrderLine);
        Optional<BeerDto> beerDtoOptional = beerService.getBeerByUpc(beerOrderLine.getUpc());

        beerDtoOptional.ifPresent(beerDto -> {
            beerOrderLineDto.setBeerId(beerDto.getId());
            beerOrderLineDto.setBeerName(beerDto.getBeerName());
            beerOrderLineDto.setBeerStyle(beerDto.getBeerStyle());
            beerOrderLineDto.setPrice(beerDto.getPrice());
        });
        return beerOrderLineDto;
    }

}
