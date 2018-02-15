package marketplace.mappers;

import org.springframework.stereotype.Component;

import marketplace.dto.BuyerDTO;
import marketplace.entity.BuyerEntity;

@Component
public class BuyerDtoMapper {
    public BuyerEntity convert(BuyerDTO dto) {
        BuyerEntity entity = new BuyerEntity();
        if(dto.getId() != null)
            entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        return entity;
    }

}
