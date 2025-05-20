package HS_hrs.vacation_service.Dto;

import HS_hrs.vacation_service.Entity.Enum.VacationType;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VacationUpdateDto {

    private Integer userId;
    private String reason;
    private LocalDate startDate;
    private LocalDate endDate;
    private VacationType vacationType;
}