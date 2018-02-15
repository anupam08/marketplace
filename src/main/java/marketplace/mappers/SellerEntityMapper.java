package marketplace.mappers;

import org.springframework.stereotype.Component;

import marketplace.dto.SellerDTO;
import marketplace.entity.SellerEntity;

@Component
public class SellerEntityMapper {
    public SellerDTO convert(SellerEntity entity) {
        SellerDTO dto = new SellerDTO();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        return dto;
    }
}
