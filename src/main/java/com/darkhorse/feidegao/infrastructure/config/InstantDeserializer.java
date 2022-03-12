package com.darkhorse.feidegao.infrastructure.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class InstantDeserializer extends JsonDeserializer<Instant> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static Instant parseToInstant(String datetime) {
        return LocalDateTime.parse(datetime, formatter).toInstant(OffsetDateTime.now().getOffset());
    }

    @Override
    public Instant deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        return Instant.from(formatter.withZone(ZoneId.of("UTC+8")).parse(parser.getText()));
    }
}
