package marketplace.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import marketplace.entity.SellerEntity;

public interface SellerRepository extends PagingAndSortingRepository<SellerEntity, Long>,
JpaSpecificationExecutor<SellerEntity> {

}
