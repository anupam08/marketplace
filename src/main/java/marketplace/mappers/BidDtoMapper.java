package marketplace.mappers;

import org.springframework.stereotype.Component;

import marketplace.dto.BidDTO;
import marketplace.entity.BidEntity;

@Component
public class BidDtoMapper {
    public BidEntity convert(BidDTO dto) {
        BidEntity entity = new BidEntity();
        if(dto.getId() != null)
            entity.setId(dto.getId());
        entity.setBidPrice(dto.getBidPrice());
        return entity;
    }

}
