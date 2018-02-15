package marketplace.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import marketplace.entity.ProjectEntity;

public interface ProjectRepository extends PagingAndSortingRepository<ProjectEntity, Long>,
JpaSpecificationExecutor<ProjectEntity> {

}
