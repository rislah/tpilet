package com.rislah.tpilet.location;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
public class LocationDto {
    @NotBlank
    @Length(min = 4, max = 50)
    private String name;
}
