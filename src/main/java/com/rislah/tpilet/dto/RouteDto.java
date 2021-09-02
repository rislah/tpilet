package com.rislah.tpilet.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.rislah.tpilet.dto.BusDto;
import com.rislah.tpilet.model.Location;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class RouteDto {
    private BusDto bus;

    @PositiveOrZero
    @NotNull
    private int busId;

    @NotBlank
    @Length(min = 4, max = 100)
    private String from;

    @NotBlank
    @Length(min = 4, max = 100)
    private String to;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    @NotNull
//    @Past
    private String departureDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    @NotNull
//    @Past
    private String arrivalDate;

    @Positive
    @NotNull
    private BigDecimal price;

    public void setFrom(Location location) {
        this.from = location.getName();
    }

    public void setTo(Location location) {
        this.to = location.getName();
    }

    @JsonSetter
    public void setTo(JsonNode jsonNode) {
        if (jsonNode.getNodeType().equals(JsonNodeType.STRING)) {
            this.to = jsonNode.asText();
        }
    }


    @JsonSetter
    public void setFrom(JsonNode jsonNode) {
        if (jsonNode.getNodeType().equals(JsonNodeType.STRING)) {
            this.from = jsonNode.asText();
        }
    }
}
