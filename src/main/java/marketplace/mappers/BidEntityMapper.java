package marketplace.mappers;

import org.springframework.stereotype.Component;

import marketplace.dto.BidDTO;
import marketplace.entity.BidEntity;

@Component
public class BidEntityMapper {
    public BidDTO convert(BidEntity entity) {
        BidDTO dto = new BidDTO();
        dto.setId(entity.getId());
        dto.setBidPrice(entity.getBidPrice());
        dto.setBuyerId(entity.getBuyer().getId());
        dto.setProjectId(entity.getProject().getId());
        return dto;
    }
}
