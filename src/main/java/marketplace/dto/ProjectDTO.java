package marketplace.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import javax.validation.constraints.NotNull;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonRootName(value = "project")
@Data
public class ProjectDTO {
    
    private Long id;
    
    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private double budget;
    
    @NotNull
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate bidDeadline;
    
    private Double lowestBid;
    
    private Long buyerId;
    
    @NotNull
    private long sellerId;
}
