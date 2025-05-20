package HS_hrs.vacation_service.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DateCalculatorResult {

    private final int totalUsedDays;
    private final int currentUsedDays;

    public int getRemainingDays() {
        return 12 - (totalUsedDays + currentUsedDays);
    }

}