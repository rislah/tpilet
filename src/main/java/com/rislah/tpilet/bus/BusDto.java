package com.rislah.tpilet.bus;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class BusDto {
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
