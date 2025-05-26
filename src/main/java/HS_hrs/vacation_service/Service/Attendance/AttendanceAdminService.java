package HS_hrs.vacation_service.Service.Attendance;

import HS_hrs.vacation_service.Dto.Attendance.Admin.ListDto;
import HS_hrs.vacation_service.Entity.Attendance;
import HS_hrs.vacation_service.Entity.Enum.AttendanceStatus;
import HS_hrs.vacation_service.Repository.Attendance.AttendanceRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AttendanceAdminService {

    private final AttendanceRepository attendanceRepository;

    /*
    모든 출 퇴근 리스트 조회
     */

    public List<ListDto> allList() {
        List<Attendance> all = attendanceRepository.findAll();
        return all.stream().map(ListDto::fromEntity).collect(Collectors.toList());
    }

    /*
    조건에 따른 검색
     */
    public List<ListDto> search(Integer userId, AttendanceStatus attendanceStatus) {

        List<Attendance> search = attendanceRepository.search(userId, attendanceStatus);

        return search.stream().map(ListDto::fromEntity).collect(Collectors.toList());
    }


}