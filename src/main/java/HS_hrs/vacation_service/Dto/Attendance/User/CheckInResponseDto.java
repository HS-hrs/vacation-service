package HS_hrs.vacation_service.Dto.Attendance.User;

import HS_hrs.vacation_service.Entity.Attendance;
import HS_hrs.vacation_service.Entity.Enum.AttendanceStatus;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CheckInResponseDto {

    private Long id;
    private Integer userId;
    private LocalDate date;
    private LocalTime checkInTime;
    private LocalTime checkOutTime;
    private AttendanceStatus status;

    public CheckInResponseDto(Attendance attendance) {
        this.id = attendance.getId();
        this.userId = attendance.getUserId();
        this.date = attendance.getDate();
        this.checkInTime = attendance.getCheckInTime();
        this.checkOutTime = attendance.getCheckOutTime();
        this.status = attendance.getStatus();
    }
}