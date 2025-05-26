package HS_hrs.vacation_service.Dto.Vacation.Admin;

import HS_hrs.vacation_service.Entity.Enum.VacationStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VacationStatusUpdateDto {

    /*
    휴가 승인 취소 DTO
     */

    private VacationStatus vacationStatus;



}