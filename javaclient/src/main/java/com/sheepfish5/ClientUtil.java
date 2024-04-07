package com.sheepfish5;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.google.protobuf.Timestamp;

public class ClientUtil {
    public static Timestamp GetTimestampFromDate(String date) {
        LocalDate localDate = LocalDate.parse(date);
        OffsetDateTime offsetDateTime = OffsetDateTime.of(localDate, 
                LocalTime.of(0, 0, 0), ZoneOffset.UTC);
        Timestamp timestamp = Timestamp.newBuilder()
                .setSeconds(offsetDateTime.toInstant().toEpochMilli()/1000L)
                .setNanos(0)
                .build();
        return timestamp;
    }

    public static String GetDateStringFromTimestamp(Timestamp timestamp) {
        Instant instant = Instant.ofEpochSecond(timestamp.getSeconds());
        OffsetDateTime offsetDateTime = OffsetDateTime.ofInstant(instant, ZoneOffset.UTC);
        return offsetDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public static Boolean VerifyStringDate(String date) {
        try {
            LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public static Long VerifyStringLong(String longValue) {
        Long l;
        try {
            l = Long.parseLong(longValue);
        } catch (NumberFormatException e) {
            return null;
        }
        return l;
    }
}
