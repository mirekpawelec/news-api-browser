package pl.pawelec.newsbrowser.service.utils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;

public class TimeUtils {

    public static ZonedDateTime now() {
        return ZonedDateTime.now();
    }

    public static ZonedDateTime parseStringToZonedDateTime(String stringDate) {
        Instant instantDateFromString = Instant.parse(stringDate);
        return instantDateFromString.atZone(ZoneId.systemDefault());
    }
}