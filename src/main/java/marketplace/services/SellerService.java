package marketplace.services;

import marketplace.dto.SellerDTO;
import marketplace.entity.SellerEntity;
import marketplace.exceptions.ObjectNotFoundException;
import marketplace.mappers.SellerDtoMapper;
import marketplace.mappers.SellerEntityMapper;
import marketplace.repositories.SellerRepository;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

@Component("sellerService")
public class SellerService {
    
    private final SellerRepository sellerRepository;
    private final SellerEntityMapper entityMapper;
    private final SellerDtoMapper dtoMapper;

    @Inject
    public SellerService(SellerRepository sellerRepository, SellerEntityMapper entityMapper,
                    SellerDtoMapper dtoMapper) {
        this.sellerRepository = sellerRepository;
        this.entityMapper = entityMapper;
        this.dtoMapper = dtoMapper;
    }

    public SellerDTO save(SellerDTO dto) {
        SellerEntity entity = sellerRepository.save(dtoMapper.convert(dto));
        return entityMapper.convert(entity);
    }

    public SellerDTO update(SellerDTO dto) {
        SellerEntity entity = sellerRepository.findOne(dto.getId());
        if (entity == null) {
            throw new ObjectNotFoundException("Seller not found");
        }
        return save(dto);
    }

    public SellerDTO findOne(Long id) {
        SellerEntity entity = sellerRepository.findOne(id);
        if (entity == null) {
            throw new ObjectNotFoundException("Seller not found");
        }
        return entityMapper.convert(entity);
    }

    public Iterable<SellerDTO> findAll() {
        Iterable<SellerEntity> sellerList = sellerRepository.findAll();
        List<SellerDTO> sellerDTOs = new ArrayList<SellerDTO>();
        for (SellerEntity seller : sellerList) {
            sellerDTOs.add(entityMapper.convert(seller));
        }
        return sellerDTOs;
    }

    public void delete(Long id) {
        SellerEntity entity = sellerRepository.findOne(id);
        if (entity == null) {
            throw new ObjectNotFoundException("Seller not found");
        }
        sellerRepository.delete(id);
    }

}

