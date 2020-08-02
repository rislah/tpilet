package com.rislah.tpilet.bus;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BusMapper {
    BusDto busToBusDto(Bus entity);

    Bus busDtoToBus(BusDto dto);

    List<BusDto> busesToBusDtos(List<Bus> entities);
}
