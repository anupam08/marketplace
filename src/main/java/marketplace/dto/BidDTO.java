package marketplace.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;

import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonRootName(value = "bid")
@Data
public class BidDTO {
    
    private Long id;

    @NotNull
    private double bidPrice;

    @NotNull
    private long buyerId;
    
    @NotNull
    private long projectId;
}
