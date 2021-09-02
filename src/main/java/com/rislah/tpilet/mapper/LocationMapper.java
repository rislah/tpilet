package com.rislah.tpilet.mapper;

import com.rislah.tpilet.dto.LocationDto;
import com.rislah.tpilet.model.Location;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LocationMapper {
    Location locationDtoToLocation(LocationDto dto);

    LocationDto locationToLocationDto(Location entity);
}
