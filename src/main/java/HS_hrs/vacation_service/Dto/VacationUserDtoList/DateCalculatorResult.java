package HS_hrs.vacation_service.Dto.VacationUserDtoList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DateCalculatorResult {

    /*
    연차 계산 할 때 사용하는 DTO
     */

    private final int totalUsedDays;
    private final int currentUsedDays;


    public int getRemainingDays() {
        return 12 - (totalUsedDays + currentUsedDays);
    }

}