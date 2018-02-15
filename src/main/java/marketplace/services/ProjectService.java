package marketplace.services;

import marketplace.dto.ProjectDTO;
import marketplace.entity.ProjectEntity;
import marketplace.entity.SellerEntity;
import marketplace.exceptions.ObjectNotFoundException;
import marketplace.exceptions.ValidationException;
import marketplace.mappers.ProjectDtoMapper;
import marketplace.mappers.ProjectEntityMapper;
import marketplace.repositories.ProjectRepository;
import marketplace.repositories.SellerRepository;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

@Component("projectService")
public class ProjectService {
    
    private final ProjectRepository projectRepository;
    private final ProjectEntityMapper entityMapper;
    private final ProjectDtoMapper dtoMapper;
    private final SellerRepository sellerRepository;

    @Inject
    public ProjectService(ProjectRepository projectRepository, ProjectEntityMapper entityMapper,
                    ProjectDtoMapper dtoMapper, SellerRepository sellerRepository) {
        this.projectRepository = projectRepository;
        this.entityMapper = entityMapper;
        this.dtoMapper = dtoMapper;
        this.sellerRepository = sellerRepository;
    }

    public ProjectDTO save(ProjectDTO dto) {
        SellerEntity sellerEntity = sellerRepository.findOne(dto.getSellerId());
        /* A project will always be associated to a seller */
        if (sellerEntity == null) {
            throw new ObjectNotFoundException("Seller not found");
        }
        ProjectEntity entity = dtoMapper.convert(dto);
        entity.setSeller(sellerEntity);
        ProjectEntity retEntity = projectRepository.save(entity);
        return entityMapper.convert(retEntity);
    }

    public ProjectDTO update(ProjectDTO dto) {
        ProjectEntity entity = projectRepository.findOne(dto.getId());
        if (entity == null) {
            throw new ObjectNotFoundException("Project not found");
        }
        /* Check to ensure that the seller of a project can not be changed */
        if(dto.getSellerId() != entity.getSeller().getId()) {
            throw new ValidationException("Cannot Update Seller");
        }
        return save(dto);
    }

    public ProjectDTO findOne(Long id) {
        ProjectEntity entity = projectRepository.findOne(id);
        if (entity == null) {
            throw new ObjectNotFoundException("Project not found");
        }
        /* Return the lowest bid and the winner only when the project's bidding
         * deadline is over 
         */
        if (entity.getBidDeadline().compareTo(LocalDate.now()) >= 0) {
            entity.setLowestBid(null);
            entity.setBuyer(null);
        }
        return entityMapper.convert(entity);
    }

    public Iterable<ProjectDTO> findAll() {
        Iterable<ProjectEntity> projectList = projectRepository.findAll();
        List<ProjectDTO> projectDTOs = new ArrayList<ProjectDTO>();
        for (ProjectEntity project : projectList) {
            /* Return the lowest bid and the winner only when the project's bidding
             * deadline is over 
             */
            if (project.getBidDeadline().compareTo(LocalDate.now()) >= 0) {
                project.setLowestBid(null);
                project.setBuyer(null);
            }
            projectDTOs.add(entityMapper.convert(project));
        }
        return projectDTOs;
    }

    public void delete(Long id) {
        ProjectEntity entity = projectRepository.findOne(id);
        if (entity == null) {
            throw new ObjectNotFoundException("Project not found");
        }
        projectRepository.delete(id);
    }
    
    public Iterable<ProjectDTO> findAllOpen() {
        Iterable<ProjectEntity> projectList = projectRepository.findAll();
        List<ProjectDTO> projectDTOs = new ArrayList<ProjectDTO>();
        for (ProjectEntity project : projectList) {
            if (project.getBidDeadline().compareTo(LocalDate.now()) >= 0) {
                /* Open projects should not return lowest bid and winner */
                project.setLowestBid(null);
                project.setBuyer(null);
                projectDTOs.add(entityMapper.convert(project));
            }
        }
        return projectDTOs;
    }
}

