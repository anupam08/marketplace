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
import marketplace.dto.BuyerDTO;
import marketplace.services.BuyerService;

@RestController
@Api(basePath = "/marketplace", value = "/buyer", description = "Buyer API")
public class BuyerController {

    private final BuyerService buyerService;

    @Inject
    public BuyerController(BuyerService buyerService) {
        this.buyerService = buyerService;
    }

    @RequestMapping(value = "/marketplace/buyer", method = RequestMethod.POST, consumes = "application/json",
                    produces = "application/json")
    @ApiOperation(value = "Create new buyer")
    public Long createBuyer(
                    @ApiParam(name = "Buyer", value = "Buyer", required = true) @Valid @RequestBody
                    BuyerDTO buyerDTO) {
        return buyerService.save(buyerDTO).getId();
    }

    @RequestMapping(value = "/marketplace/buyer/{id}", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Get buyer by Id")
    public BuyerDTO getBuyer(@ApiParam(value = "Buyer ID", required = true) @PathVariable long id) {
        return buyerService.findOne(id);
    }

    @RequestMapping(value = "/marketplace/buyer", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Get all buyers")
    public Iterable<BuyerDTO> getBuyers() {
        return buyerService.findAll();
    }

    @RequestMapping(value = "/marketplace/buyer/{id}", method = RequestMethod.PUT, consumes = "application/json",
                    produces = "application/json")
    @ApiOperation(value = "Update buyer by Id")
    public BuyerDTO updateBuyer(
                    @ApiParam(value = "Buyer ID", required = true) @PathVariable Long id,
                    @ApiParam(name = "Buyer", value = "Buyer", required = true) @Valid @RequestBody
                    BuyerDTO buyerDTO) {
        buyerDTO.setId(id);
        return buyerService.update(buyerDTO);
    }

    @RequestMapping(value = "/marketplace/buyer/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete buyer by Id")
    public void deleteBuyer(@ApiParam(value = "Buyer ID", required = true) @PathVariable long id) {
        buyerService.delete(id);
    }

}

