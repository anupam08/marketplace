package marketplace.services;

import marketplace.dto.BuyerDTO;
import marketplace.entity.BuyerEntity;
import marketplace.exceptions.ObjectNotFoundException;
import marketplace.mappers.BuyerDtoMapper;
import marketplace.mappers.BuyerEntityMapper;
import marketplace.repositories.BuyerRepository;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

@Component("buyerService")
public class BuyerService {
    
    private final BuyerRepository buyerRepository;
    private final BuyerEntityMapper entityMapper;
    private final BuyerDtoMapper dtoMapper;

    @Inject
    public BuyerService(BuyerRepository buyerRepository, BuyerEntityMapper entityMapper,
                    BuyerDtoMapper dtoMapper) {
        this.buyerRepository = buyerRepository;
        this.entityMapper = entityMapper;
        this.dtoMapper = dtoMapper;
    }

    public BuyerDTO save(BuyerDTO dto) {
        BuyerEntity entity = buyerRepository.save(dtoMapper.convert(dto));
        return entityMapper.convert(entity);
    }

    public BuyerDTO update(BuyerDTO dto) {
        BuyerEntity entity = buyerRepository.findOne(dto.getId());
        if (entity == null) {
            throw new ObjectNotFoundException("Buyer not found");
        }
        return save(dto);
    }

    public BuyerDTO findOne(Long id) {
        BuyerEntity entity = buyerRepository.findOne(id);
        if (entity == null) {
            throw new ObjectNotFoundException("Buyer not found");
        }
        return entityMapper.convert(entity);
    }

    public Iterable<BuyerDTO> findAll() {
        Iterable<BuyerEntity> buyerList = buyerRepository.findAll();
        List<BuyerDTO> buyerDTOs = new ArrayList<BuyerDTO>();
        for (BuyerEntity buyer : buyerList) {
            buyerDTOs.add(entityMapper.convert(buyer));
        }
        return buyerDTOs;
    }

    public void delete(Long id) {
        BuyerEntity entity = buyerRepository.findOne(id);
        if (entity == null) {
            throw new ObjectNotFoundException("Buyer not found");
        }
        buyerRepository.delete(id);
    }

}

