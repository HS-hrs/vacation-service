package HS_hrs.vacation_service.Dto.VacationAdminDtoList;

import HS_hrs.vacation_service.Entity.Enum.VacationStatus;
import HS_hrs.vacation_service.Entity.Enum.VacationType;
import HS_hrs.vacation_service.Entity.Vacation;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AllVacationListDto {

    private Long id;
    private Integer userId;
    private Integer leftDayCount;
    private Integer usedDayCount;
    private LocalDate startDate;
    private LocalDate endDate;
    private VacationType vacationType;
    private String reason;
    private VacationStatus vacationStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public static AllVacationListDto fromEntity(Vacation vacation) {

        AllVacationListDto dto = new AllVacationListDto();

        dto.id = vacation.getId();
        dto.userId = vacation.getUserId();
        dto.leftDayCount = vacation.getLeftDayCount();
        dto.usedDayCount = vacation.getUsedDayCount();
        dto.startDate = vacation.getStartDate();
        dto.endDate = vacation.getEndDate();
        dto.vacationType = vacation.getVacationType();
        dto.reason = vacation.getReason();
        dto.vacationStatus = vacation.getStatus();
        dto.createdAt = vacation.getCreatedAt();
        dto.updatedAt = vacation.getUpdatedAt() != null ? vacation.getUpdatedAt() : null;

        return dto;
    }

}