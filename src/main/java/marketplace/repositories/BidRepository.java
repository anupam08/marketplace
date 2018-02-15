package marketplace.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import marketplace.entity.BidEntity;

public interface BidRepository extends PagingAndSortingRepository<BidEntity, Long>,
JpaSpecificationExecutor<BidEntity> {
    BidEntity findByBuyerIdAndProjectId(Long buyerId, Long projectId);
}
