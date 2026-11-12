package com.techflow.ws.sys.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

class UtilsTest {

    private static final String FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    @Test
    void interpretsLocalTimeInTheGivenZone() {
        // 16:28 in Sao Paulo (UTC-3) is 19:28 UTC
        assertEquals(Instant.parse("2026-11-10T19:28:53Z"),
                Utils.parseToInstant("2026-11-10T16:28:53", FORMAT, ZoneId.of("America/Sao_Paulo")));
    }

    @Test
    void supportsUtcExplicitly() {
        assertEquals(Instant.parse("2026-11-10T16:28:53Z"),
                Utils.parseToInstant("2026-11-10T16:28:53", FORMAT, ZoneOffset.UTC));
    }

    @Test
    void defaultsToTheApplicationZone() {
        assertEquals(Utils.parseToInstant("2026-11-10T16:28:53", FORMAT, Utils.APP_ZONE),
                Utils.parseToInstant("2026-11-10T16:28:53", FORMAT));
    }

    @Test
    void returnsNullForNullOrEmptyInput() {
        assertNull(Utils.parseToInstant(null, FORMAT));
        assertNull(Utils.parseToInstant("", FORMAT));
    }

    @Test
    void rejectsInputOutsideThePattern() {
        assertThrows(DateTimeParseException.class, () -> Utils.parseToInstant("10/11/2026", FORMAT));
    }
}
