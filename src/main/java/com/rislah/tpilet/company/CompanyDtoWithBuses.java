package com.rislah.tpilet.company;

import java.util.List;

import com.rislah.tpilet.bus.BusDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CompanyDtoWithBuses extends CompanyDto {
    private List<BusDto> buses;
}
