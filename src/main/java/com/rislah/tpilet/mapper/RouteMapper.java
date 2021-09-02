package com.rislah.tpilet.mapper;

import com.rislah.tpilet.dto.RouteDto;
import com.rislah.tpilet.model.Location;
import com.rislah.tpilet.model.Route;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", uses = {BusMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RouteMapper {
    Location nameToLocation(String name);

    String locationToName(Location location);

    RouteDto routeToRouteDto(Route route);

//    public String offsetDateTimeToString(OffsetDateTime time) {
//        return time.toString();
//    }
//
//    public OffsetDateTime stringToOffsetDateTime(String time) {
//        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-uuuu'T'HH:mmXXXXX", Locale.ENGLISH);
//        OffsetDateTime offsetDateTime = OffsetDateTime.parse(time, format);
//        return offsetDateTime;
//    }

    Route routeDtoToRoute(RouteDto dto);

    List<RouteDto> routesToRouteDtos(List<Route> entities);
}
