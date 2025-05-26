package HS_hrs.vacation_service.Repository.Attendance;

import HS_hrs.vacation_service.Entity.Attendance;
import HS_hrs.vacation_service.Entity.Enum.AttendanceStatus;
import java.util.List;

public interface AttendanceRepositoryCustom {

    List<Attendance> search(Integer userId, AttendanceStatus status);
}