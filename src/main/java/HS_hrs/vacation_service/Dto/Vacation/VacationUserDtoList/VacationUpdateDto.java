package HS_hrs.vacation_service.Dto.Vacation.VacationUserDtoList;

import HS_hrs.vacation_service.Entity.Enum.VacationType;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VacationUpdateDto {

    /*
    휴가 업데이트 DTO
     */

    private Integer userId;
    private String reason;
    private LocalDate startDate;
    private LocalDate endDate;
    private VacationType vacationType;
}