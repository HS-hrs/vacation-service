package HS_hrs.vacation_service.Controller;

import HS_hrs.vacation_service.Dto.VacationAdminDtoList.AllVacationListDto;
import HS_hrs.vacation_service.Entity.Vacation;
import HS_hrs.vacation_service.Service.VacationAdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
}