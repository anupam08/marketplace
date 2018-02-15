package marketplace.services;

import marketplace.dto.BidDTO;
import marketplace.entity.BidEntity;
import marketplace.entity.BuyerEntity;
import marketplace.entity.ProjectEntity;
import marketplace.exceptions.ObjectNotFoundException;
import marketplace.exceptions.ValidationException;
import marketplace.mappers.BidDtoMapper;
import marketplace.mappers.BidEntityMapper;
import marketplace.repositories.BidRepository;
import marketplace.repositories.BuyerRepository;
import marketplace.repositories.ProjectRepository;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

import javax.inject.Inject;

@Component("bidService")
public class BidService {
    
    private final ProjectRepository projectRepository;
    private final BuyerRepository buyerRepository;
    private final BidRepository bidRepository;
    private final BidEntityMapper entityMapper;
    private final BidDtoMapper dtoMapper;

    @Inject
    public BidService(ProjectRepository projectRepository, BuyerRepository buyerRepository, 
                    BidRepository bidRepository, BidEntityMapper entityMapper,
                    BidDtoMapper dtoMapper) {
        this.projectRepository = projectRepository;
        this.buyerRepository = buyerRepository;
        this.bidRepository = bidRepository;
        this.entityMapper = entityMapper;
        this.dtoMapper = dtoMapper;
    }

    public BidDTO save(BidDTO dto) {
        ProjectEntity projectEntity = projectRepository.findOne(dto.getProjectId());
        if (projectEntity == null) {
            throw new ObjectNotFoundException("Project not found");
        }
        if (projectEntity.getBidDeadline().compareTo(LocalDate.now()) < 0) {
            throw new ValidationException("Project's bidding deadline is over");
        }
        BuyerEntity buyerEntity = buyerRepository.findOne(dto.getBuyerId());
        if (buyerEntity == null) {
            throw new ObjectNotFoundException("Buyer not found");
        }
        /* Check that the user is not placing multiple bids on the same project.
         * User can update his already placed bid through the update API
         */
        BidEntity existingEntity = bidRepository.findByBuyerIdAndProjectId(dto.getBuyerId(), dto.getProjectId());
        if(existingEntity != null) {
            throw new ValidationException("Cannot Place multiple bids");
        }
        if(dto.getBidPrice() <= 0) {
            throw new ValidationException("Bid price should be greater than 0");
        }
        BidEntity entity = dtoMapper.convert(dto);
        entity.setBuyer(buyerEntity);
        entity.setProject(projectEntity);
        BidEntity retEntity = bidRepository.save(entity);
        /* Update the lowest bid and the buyer if this request contains the lowest bid */
        if(projectEntity.getLowestBid() == 0 || entity.getBidPrice() < projectEntity.getLowestBid()) {
            projectEntity.setBuyer(buyerEntity);
            projectEntity.setLowestBid(entity.getBidPrice());
            projectRepository.save(projectEntity);
        }
        return entityMapper.convert(retEntity);
    }

    public BidDTO update(BidDTO dto) {
        BidEntity entity = bidRepository.findOne(dto.getId());
        if (entity == null) {
            throw new ObjectNotFoundException("Bid not found");
        }
        ProjectEntity projectEntity = projectRepository.findOne(entity.getProject().getId());
        /* Check if project deadline is over */
        if (projectEntity.getBidDeadline().compareTo(LocalDate.now()) < 0) {
            throw new ValidationException("Project's bidding deadline is over");
        }
        if(dto.getProjectId() != entity.getProject().getId()) {
            throw new ValidationException("Cannot Update Project");
        }
        BuyerEntity buyerEntity = buyerRepository.findOne(entity.getBuyer().getId());
        if(dto.getBuyerId() != entity.getBuyer().getId()) {
            throw new ValidationException("Cannot Update Buyer");
        }
        if(dto.getBidPrice() <= 0) {
            throw new ValidationException("Bid price should be greater than 0");
        }
        /* A buyer can lower his bid multiple times but he can not increase his previously submitted bid
         * This is done to avoid the case when buyer can initially submit a very low bid which can result
         * in other buyers not winning the project and then he can increase
         * this value just before the deadline 
         */
        if(dto.getBidPrice() > entity.getBidPrice()) {
            throw new ValidationException("Cannot increase the bid price");
        }
        entity.setBidPrice(dto.getBidPrice());
        BidEntity retEntity = bidRepository.save(entity);
        /* Update the lowest bid and the buyer if this request contains the lowest bid */
        if(projectEntity.getLowestBid() == 0 || entity.getBidPrice() < projectEntity.getLowestBid()) {
            projectEntity.setBuyer(buyerEntity);
            projectEntity.setLowestBid(entity.getBidPrice());
            projectRepository.save(projectEntity);
        }
        return entityMapper.convert(retEntity);
    }

    public BidDTO findOne(Long id) {
        BidEntity entity = bidRepository.findOne(id);
        if (entity == null) {
            throw new ObjectNotFoundException("Bid not found");
        }
        return entityMapper.convert(entity);
    }

}

