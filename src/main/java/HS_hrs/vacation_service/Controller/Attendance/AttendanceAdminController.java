package HS_hrs.vacation_service.Controller.Attendance;

import HS_hrs.vacation_service.Dto.Attendance.Admin.ListDto;
import HS_hrs.vacation_service.Dto.Vacation.Admin.AllVacationListDto;
import HS_hrs.vacation_service.Entity.Enum.AttendanceStatus;
import HS_hrs.vacation_service.Entity.Enum.VacationStatus;
import HS_hrs.vacation_service.Entity.Enum.VacationType;
import HS_hrs.vacation_service.Service.Attendance.AttendanceAdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/attendance/admin")
@Tag(name = "Admin Attendance API", description = "관리자 전용 Attendance API")
@RequiredArgsConstructor
public class AttendanceAdminController {

    private final AttendanceAdminService adminService;

    @GetMapping("list")
    @Operation(summary = "모든 attendance List", description = "모든 출 퇴근 리스트")
    public ResponseEntity<List<ListDto>> attendanceList() {
        List<ListDto> list = adminService.allList();

        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/list/search")
    @Operation(summary = "조건별 목록 검색", description = "조건에 따라 출퇴근 검색.")
    @Parameters({
        @Parameter(name = "userId", description = "사용자 ID", in = ParameterIn.QUERY),
        @Parameter(name = "AttendanceStatus", description = "휴가 상태", in = ParameterIn.QUERY)
    })
    public ResponseEntity<List<ListDto>> searchAttendance(
        @RequestParam(required = false) Integer userId,
        @RequestParam(required = false) AttendanceStatus attendanceStatus
    ) {
        List<ListDto> list = adminService.search(userId, attendanceStatus);
        return ResponseEntity.ok(list);
    }

}