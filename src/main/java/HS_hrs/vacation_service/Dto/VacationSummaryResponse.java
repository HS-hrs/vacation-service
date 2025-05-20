package HS_hrs.vacation_service.Dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VacationSummaryResponse {

    /*
    휴가 전체 목록 반환 DTO
     */
    private List<MyVacationListDto> vacationList;
    private int usedDays;
    private int remainingDays;
}