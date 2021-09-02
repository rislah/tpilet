package com.rislah.tpilet.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class BusDto {
    private int id;
    @PositiveOrZero
    @NotNull
    private int companyId;
    @PositiveOrZero
    @NotNull
    private int number;
    @NotNull
    private boolean wc;
    @NotNull
    private boolean wifi;
    @NotNull
    private boolean ac;
}
