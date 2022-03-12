package com.darkhorse.feidegao.infrastructure.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class InstantSerializer extends JsonSerializer<Instant> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(OffsetDateTime.now().getOffset());

    public static String parseToString(Instant instant) {
        return formatter.format(instant);
    }

    @Override
    public void serialize(Instant value, JsonGenerator generator, SerializerProvider serializers) throws IOException {
        generator.writeString(formatter.format(value));
    }
}