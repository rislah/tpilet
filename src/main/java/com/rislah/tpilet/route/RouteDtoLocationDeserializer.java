package com.rislah.tpilet.route;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.rislah.tpilet.location.Location;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class RouteDtoLocationDeserializer extends StdDeserializer<String> {
    public RouteDtoLocationDeserializer() {
        this(null);
    }

    public RouteDtoLocationDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = p.getCodec().readTree(p);
        String name = node.get("location").asText();

        log.info("{}", node);
        return name;
    }
}
