package store.utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeManager {
    public static LocalDateTime parse(String rawTime) {
        return LocalDateTime.parse(rawTime+"T00:00", getDateFormatter());
    }

    private static DateTimeFormatter getDateFormatter() {
        return DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    }
}
