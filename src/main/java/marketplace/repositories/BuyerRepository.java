package marketplace.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import marketplace.entity.BuyerEntity;

public interface BuyerRepository extends PagingAndSortingRepository<BuyerEntity, Long>,
JpaSpecificationExecutor<BuyerEntity> {

}
