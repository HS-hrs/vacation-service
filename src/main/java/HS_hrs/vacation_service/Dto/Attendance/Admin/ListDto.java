package HS_hrs.vacation_service.Dto.Attendance.Admin;

import HS_hrs.vacation_service.Entity.Attendance;
import HS_hrs.vacation_service.Entity.Enum.AttendanceStatus;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListDto {

    private Long id;
    private Integer userId;
    private LocalDate date;
    private LocalTime checkInTime;
    private LocalTime checkOutTime;
    private AttendanceStatus status;

    public static ListDto fromEntity(Attendance attendance){
        ListDto dto = new ListDto();

        dto.id = attendance.getId();
        dto.userId = attendance.getUserId();
        dto.date = attendance.getDate();
        dto.checkInTime = attendance.getCheckInTime();
        dto.checkOutTime = attendance.getCheckOutTime() != null ? attendance.getCheckOutTime() : null;
        dto.status = attendance.getStatus();

        return dto;
    }

}