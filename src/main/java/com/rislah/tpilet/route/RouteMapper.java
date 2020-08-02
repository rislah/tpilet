package com.rislah.tpilet.route;

import com.rislah.tpilet.bus.BusMapper;
import com.rislah.tpilet.location.Location;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;
import java.util.Locale;

@Mapper(componentModel = "spring", uses = {BusMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class RouteMapper {
    public Location nameToLocation(String name) {
        return Location.builder().name(name).build();
    }

    public String locationToName(Location location) {
        return location.getName();
    }

//    public String offsetDateTimeToString(OffsetDateTime time) {
//        return time.toString();
//    }
//
//    public OffsetDateTime stringToOffsetDateTime(String time) {
//        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-uuuu'T'HH:mmXXXXX", Locale.ENGLISH);
//        OffsetDateTime offsetDateTime = OffsetDateTime.parse(time, format);
//        return offsetDateTime;
//    }

    public abstract Route routeDtoToRoute(RouteDto dto);

    public abstract List<RouteDto> routesToRouteDtos(List<Route> entities);
}
