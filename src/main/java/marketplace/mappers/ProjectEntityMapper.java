package marketplace.mappers;

import org.springframework.stereotype.Component;

import marketplace.dto.ProjectDTO;
import marketplace.entity.ProjectEntity;

@Component
public class ProjectEntityMapper {
    public ProjectDTO convert(ProjectEntity entity) {
        ProjectDTO dto = new ProjectDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setBudget(entity.getBudget());
        dto.setBidDeadline(entity.getBidDeadline());
        dto.setLowestBid(entity.getLowestBid());
        if(entity.getBuyer() != null)
            dto.setBuyerId(entity.getBuyer().getId());
        else
            dto.setBuyerId(null);
        dto.setSellerId(entity.getSeller().getId());
        return dto;
    }
}
