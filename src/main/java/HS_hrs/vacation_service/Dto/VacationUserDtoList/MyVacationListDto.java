package HS_hrs.vacation_service.Dto.VacationUserDtoList;

import HS_hrs.vacation_service.Entity.Enum.VacationStatus;
import HS_hrs.vacation_service.Entity.Vacation;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyVacationListDto {

    /*
     휴가 목록 스트림으로 값을 넣을때 사용하는 DTO
     */

    public Long id;
    public Integer leftDayCount;
    public Integer usedDayCount;
    public LocalDate starDate;
    public LocalDate endDate;
    public VacationStatus vacationStatus;


    public static MyVacationListDto fromEntity(Vacation vacation){
        MyVacationListDto dto = new MyVacationListDto();

        dto.id = vacation.getId();
        dto.leftDayCount = vacation.getLeftDayCount();
        dto.usedDayCount = vacation.getUsedDayCount();
        dto.starDate = vacation.getStartDate();
        dto.endDate = vacation.getEndDate();
        dto.vacationStatus = vacation.getStatus();

        return dto;
    }
}