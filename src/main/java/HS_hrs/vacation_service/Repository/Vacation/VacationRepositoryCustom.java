package HS_hrs.vacation_service.Repository.Vacation;

import HS_hrs.vacation_service.Entity.Enum.VacationStatus;
import HS_hrs.vacation_service.Entity.Enum.VacationType;
import HS_hrs.vacation_service.Entity.Vacation;
import java.util.List;

public interface VacationRepositoryCustom {

    List<Vacation> search(Integer userId, VacationType vacationType, VacationStatus vacationStatus);
}