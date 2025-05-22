package HS_hrs.vacation_service.Controller;

import HS_hrs.vacation_service.Dto.VacationAdminDtoList.AllVacationListDto;
import HS_hrs.vacation_service.Entity.Enum.VacationStatus;
import HS_hrs.vacation_service.Entity.Enum.VacationType;
import HS_hrs.vacation_service.Entity.Vacation;
import HS_hrs.vacation_service.Service.VacationAdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vacation/admin")
@RequiredArgsConstructor
@Tag(name = "Admin Vacation API", description = "관리자 전용 Vacation API")
public class VacationAdminController {

    private final VacationAdminService vacationAdminService;

    @GetMapping("/vacationList")
    @Operation(summary = "모든 user Vacation List", description = "모든 휴가 리스트")
    public ResponseEntity<List<AllVacationListDto>> vacationList() {
        List<AllVacationListDto> list = vacationAdminService.AllList();

        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(list);
    }

    @GetMapping("/vacationList/search")
    @Operation(summary = "조건별 목록 검색", description = "조건에 따라 휴가 목록을 검색한다.")
    @Parameters({
        @Parameter(name = "userId", description = "사용자 ID", in = ParameterIn.QUERY),
        @Parameter(name = "vacationType", description = "휴가 유형", in = ParameterIn.QUERY),
        @Parameter(name = "vacationStatus", description = "휴가 상태", in = ParameterIn.QUERY)
    })
    public ResponseEntity<List<AllVacationListDto>> searchVacations(
        @RequestParam(required = false) Integer userId,
        @RequestParam(required = false) VacationType vacationType,
        @RequestParam(required = false) VacationStatus vacationStatus
    ) {
        List<AllVacationListDto> list = vacationAdminService.search(userId, vacationType, vacationStatus);
        return ResponseEntity.ok(list);
    }
}