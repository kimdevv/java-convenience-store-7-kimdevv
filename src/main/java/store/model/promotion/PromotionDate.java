package store.model.promotion;

import camp.nextstep.edu.missionutils.DateTimes;
import store.utility.DateTimeManager;

import java.time.LocalDateTime;

public class PromotionDate {
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    protected PromotionDate(String startDate, String endDate) {
        this.startDate = DateTimeManager.parse(startDate);
        this.endDate = DateTimeManager.parse(endDate);
    }
}
