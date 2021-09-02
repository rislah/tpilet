package com.rislah.tpilet.mapper;

import com.rislah.tpilet.dto.BusDto;
import com.rislah.tpilet.model.Bus;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BusMapper {
    BusDto busToBusDto(Bus entity);

    Bus busDtoToBus(BusDto dto);

    List<BusDto> busesToBusDtos(List<Bus> entities);
}
