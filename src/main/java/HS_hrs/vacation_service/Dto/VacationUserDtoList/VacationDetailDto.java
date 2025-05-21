package HS_hrs.vacation_service.Dto.VacationUserDtoList;

import HS_hrs.vacation_service.Entity.Enum.VacationStatus;
import HS_hrs.vacation_service.Entity.Enum.VacationType;
import HS_hrs.vacation_service.Entity.Vacation;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class VacationDetailDto {

    /*
    상세 휴가 정보 DTO
     */

    private Long id;
    private Integer leftDayCount;
    private Integer usedDayCount;
    private VacationType vacationType;
    private String reason;
    private VacationStatus vacationStatus;
    private LocalDate startDate;
    private LocalDate endDate;

    public VacationDetailDto(Vacation vacation) {
        this.id = vacation.getId();
        this.leftDayCount = vacation.getLeftDayCount();
        this.usedDayCount = vacation.getUsedDayCount();
        this.vacationType = vacation.getVacationType();
        this.reason = vacation.getReason();
        this.vacationStatus = vacation.getStatus();
        this.startDate = vacation.getStartDate();
        this.endDate = vacation.getEndDate();
    }
}