package HS_hrs.vacation_service.Service.Attendance;

import HS_hrs.vacation_service.Dto.Attendance.User.CheckInRequestDto;
import HS_hrs.vacation_service.Dto.Attendance.User.CheckInResponseDto;
import HS_hrs.vacation_service.Entity.Attendance;
import HS_hrs.vacation_service.Repository.Attendance.AttendanceRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AttendanceUserService {

    private final AttendanceRepository attendanceRepository;

    /*
    출근 등록
     */

    @Transactional
    public CheckInResponseDto checkIn(CheckInRequestDto dto) {

        Attendance attendance = Attendance.checkIn(dto.getUserId());

        attendanceRepository.save(attendance);

        return new CheckInResponseDto(attendance);
    }

    /*
    퇴근
     */

    @Transactional
    public CheckInResponseDto checkOut(CheckInRequestDto dto) {
        Attendance attendance = attendanceRepository.findByUserId(dto.getUserId())
            .orElseThrow(() -> new IllegalArgumentException("출근 정보가 없습니다."));

        attendance.checkOut(dto.getUserId());

        attendanceRepository.save(attendance);


        return new CheckInResponseDto(attendance);
    }

}