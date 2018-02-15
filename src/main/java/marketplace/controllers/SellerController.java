package marketplace.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.validation.Valid;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import marketplace.dto.SellerDTO;
import marketplace.services.SellerService;

@RestController
@Api(basePath = "/marketplace", value = "/seller", description = "Seller API")
public class SellerController {

    private final SellerService sellerService;

    @Inject
    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @RequestMapping(value = "/marketplace/seller", method = RequestMethod.POST, consumes = "application/json",
                    produces = "application/json")
    @ApiOperation(value = "Create new seller")
    public Long createSeller(
                    @ApiParam(name = "Seller", value = "Seller", required = true) @Valid @RequestBody
                    SellerDTO sellerDTO) {
        return sellerService.save(sellerDTO).getId();
    }

    @RequestMapping(value = "/marketplace/seller/{id}", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Get seller by Id")
    public SellerDTO getSeller(@ApiParam(value = "Seller ID", required = true) @PathVariable long id) {
        return sellerService.findOne(id);
    }

    @RequestMapping(value = "/marketplace/seller", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Get all sellers")
    public Iterable<SellerDTO> getSellers() {
        return sellerService.findAll();
    }

    @RequestMapping(value = "/marketplace/seller/{id}", method = RequestMethod.PUT, consumes = "application/json",
                    produces = "application/json")
    @ApiOperation(value = "Update seller by Id")
    public SellerDTO updateSeller(
                    @ApiParam(value = "Seller ID", required = true) @PathVariable Long id,
                    @ApiParam(name = "Seller", value = "Seller", required = true) @Valid @RequestBody
                    SellerDTO sellerDTO) {
        sellerDTO.setId(id);
        return sellerService.update(sellerDTO);
    }

    @RequestMapping(value = "/marketplace/seller/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete seller by Id")
    public void deleteSeller(@ApiParam(value = "Seller ID", required = true) @PathVariable long id) {
        sellerService.delete(id);
    }

}

