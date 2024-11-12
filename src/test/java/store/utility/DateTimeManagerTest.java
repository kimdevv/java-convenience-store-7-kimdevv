package store.utility;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

public class DateTimeManagerTest {

    @Test
    public void 문자열로부터_날짜를_파싱한다() {
        String date = "2024-01-31";
        LocalDateTime parsedDate = DateTimeManager.parse(date);
        assertThat(parsedDate.getYear()).isEqualTo(2024);
        assertThat(parsedDate.getMonthValue()).isEqualTo(1);
        assertThat(parsedDate.getDayOfMonth()).isEqualTo(31);
        assertThat(parsedDate.getHour()).isEqualTo(0);
        assertThat(parsedDate.getMinute()).isEqualTo(0);
    }
}
