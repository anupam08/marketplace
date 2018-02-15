package marketplace.mappers;

import org.springframework.stereotype.Component;

import marketplace.dto.ProjectDTO;
import marketplace.entity.ProjectEntity;

@Component
public class ProjectDtoMapper {
    public ProjectEntity convert(ProjectDTO dto) {
        ProjectEntity entity = new ProjectEntity();
        if(dto.getId() != null)
            entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setBudget(dto.getBudget());
        entity.setBidDeadline(dto.getBidDeadline());
        return entity;
    }

}
