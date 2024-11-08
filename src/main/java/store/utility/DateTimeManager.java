package store.utility;

import java.time.LocalDateTime;

public class DateTimeManager {
    public static LocalDateTime parse(String rawTime) {
        return LocalDateTime.parse(rawTime);
    }
}
