package marketplace.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;

import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonRootName(value = "buyer")
@Data
public class BuyerDTO {
    
    private Long id;
    
    @NotNull
    private String firstName;

    @NotNull
    private String lastName;
}
