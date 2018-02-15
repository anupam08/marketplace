package marketplace.mappers;

import org.springframework.stereotype.Component;

import marketplace.dto.BuyerDTO;
import marketplace.entity.BuyerEntity;

@Component
public class BuyerEntityMapper {
    public BuyerDTO convert(BuyerEntity entity) {
        BuyerDTO dto = new BuyerDTO();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        return dto;
    }
}
