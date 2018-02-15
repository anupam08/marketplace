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
import marketplace.dto.BidDTO;
import marketplace.services.BidService;

@RestController
@Api(basePath = "/marketplace", value = "/bid", description = "Bid API")
public class BidController {

    private final BidService bidService;

    @Inject
    public BidController(BidService bidService) {
        this.bidService = bidService;
    }
    
    @RequestMapping(value = "/marketplace/bid", method = RequestMethod.POST, consumes = "application/json",
                    produces = "application/json")
    @ApiOperation(value = "Create new bid")
    public Long createBid(
                    @ApiParam(name = "bid", value = "bid", required = true) @Valid @RequestBody
                    BidDTO bidDTO) {
        return bidService.save(bidDTO).getId();
    }
    
    @RequestMapping(value = "/marketplace/bid/{id}", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Get bid by Id")
    public BidDTO getBid(@ApiParam(value = "Bid ID", required = true) @PathVariable long id) {
        return bidService.findOne(id);
    }
    
    @RequestMapping(value = "/marketplace/bid/{id}", method = RequestMethod.PUT, consumes = "application/json",
                    produces = "application/json")
    @ApiOperation(value = "Update bid by Id")
    public BidDTO updateBid(
                    @ApiParam(value = "Bid ID", required = true) @PathVariable Long id,
                    @ApiParam(name = "Bid", value = "Bid", required = true) @Valid @RequestBody
                    BidDTO bidDTO) {
        bidDTO.setId(id);
        return bidService.update(bidDTO);
    }

}

