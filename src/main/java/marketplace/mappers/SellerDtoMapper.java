package marketplace.mappers;

import org.springframework.stereotype.Component;

import marketplace.dto.SellerDTO;
import marketplace.entity.SellerEntity;

@Component
public class SellerDtoMapper {
    public SellerEntity convert(SellerDTO dto) {
        SellerEntity entity = new SellerEntity();
        if(dto.getId() != null)
            entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        return entity;
    }

}
