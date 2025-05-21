package HS_hrs.vacation_service.Controller;

import HS_hrs.vacation_service.Dto.VacationUserDtoList.VacationDetailDto;
import HS_hrs.vacation_service.Dto.VacationUserDtoList.VacationRequestDto;
import HS_hrs.vacation_service.Dto.VacationUserDtoList.VacationSummaryResponse;
import HS_hrs.vacation_service.Dto.VacationUserDtoList.VacationUpdateDto;
import HS_hrs.vacation_service.Service.VacationUserService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.env.Environment;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "User Vacation API", description = "유저 전용 Vacation API")
@Slf4j     
@RestController
@RequestMapping("/vacation/user")
@RequiredArgsConstructor
public class VacationUserController {

  private final VacationUserService vacationUserService;
  private final Environment env;

    @GetMapping
    @Operation(summary = "vacation 서비스 홈", description = "vacation 서비스 기본 엔드포인트")
    public String home() {
        return "vacation 서비스입니다.";
    }

    @GetMapping("/check")
    @Operation(summary = "서버 포트 확인", description = "서버 포트 확인 엔드포인트")
    public String check(HttpServletRequest request) {
      log.info("Server port={}", request.getServerPort());
      return String.format("Check from Server running at port %s", env.getProperty("local.server.port"));
    }

    @PostMapping("/requestLeave")
    @Operation(summary = "USER 휴가 신청", description = "휴가 신청 엔드포인트")
    public ResponseEntity<Void> RequestLeave(@Valid @RequestBody VacationRequestDto dto) {
       vacationUserService.RequestLeave(dto);
      return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/vacationDetail/{id}")
    @Operation(summary = "상세 휴가 조희", description = "내 상세 휴가 정보 조회")
    @Parameters({
        @Parameter(name = "id",description = "id", in = ParameterIn.PATH),
    })
    public ResponseEntity<VacationDetailDto> getVacationDetail(@PathVariable("id") Long id) {
      VacationDetailDto vacationDetail = vacationUserService.getVacationDetail(id);
      return ResponseEntity.ok(vacationDetail);
    }

  @PostMapping("/vacationDetail/{id}")
  @Operation(summary = "상세 휴가 업데이트", description = "내 상세 휴가 정보 수정")
  @Parameters({
      @Parameter(name = "id",description = "id", in = ParameterIn.PATH),
  })
  public ResponseEntity<VacationDetailDto> updateVacationDetail(@PathVariable("id") Long id,
  @RequestBody VacationUpdateDto updateDto) {

    VacationDetailDto vacationDetailDto = vacationUserService.updateVacationDetail(id, updateDto);
    return ResponseEntity.ok().body(vacationDetailDto);
  }

  @PostMapping("/vacationDetail/{id}/cancel")
  @Operation(summary = "상세 휴가 삭제", description = "내 상세 휴가 정보 삭제")
  @Parameters({
      @Parameter(name = "id",description = "id", in = ParameterIn.PATH),
  })
  public ResponseEntity<String> cancelVacationDetail(@PathVariable("id") Long id) {
    Long cancelId = vacationUserService.cancelVacation(id);
    return ResponseEntity.ok().body(cancelId + "번 휴가 취소되었습니다.");
  }

  @GetMapping("/myVacationList/{userId}/")
  @Operation(summary = "전체 휴가 조회", description = "내 전체 휴가 정보 조회")
  @Parameters({
      @Parameter(name = "userId",description = "userId", in = ParameterIn.PATH),
  })
  public ResponseEntity<VacationSummaryResponse> MyVacationList(@PathVariable("userId") Integer userId){
    VacationSummaryResponse myVacationList = vacationUserService.getMyVacationList(userId);
    return ResponseEntity.ok(myVacationList);
  }


}