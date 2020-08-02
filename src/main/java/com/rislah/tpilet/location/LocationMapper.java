package com.rislah.tpilet.location;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LocationMapper {
    Location locationDtoToLocation(LocationDto dto);

    LocationDto locationToLocationDto(Location entity);

    List<LocationDto> locationsToLocationDtos(List<Location> entities);
}
