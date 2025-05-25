package HS_hrs.vacation_service.Dto.Vacation.VacationAdminDtoList;

import HS_hrs.vacation_service.Dto.Vacation.VacationUserDtoList.VacationDetailDto;
import HS_hrs.vacation_service.Entity.Vacation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserVacationDetailDto  extends VacationDetailDto {

    /*
    사용자 휴가 정보 DTO
     */

    private Integer userId;

    public UserVacationDetailDto(Vacation vacation) {
        super(vacation);
        this.userId = vacation.getUserId();
    }
}