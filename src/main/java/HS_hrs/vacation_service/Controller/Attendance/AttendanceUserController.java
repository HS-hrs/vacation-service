package HS_hrs.vacation_service.Controller.Attendance;

import HS_hrs.vacation_service.Dto.Attendance.User.CheckInRequestDto;
import HS_hrs.vacation_service.Dto.Attendance.User.CheckInResponseDto;
import HS_hrs.vacation_service.Service.Attendance.AttendanceUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User Attendance API", description = "유저 전용 Attendance API")
@RestController
@RequestMapping("/attendance/user")
@RequiredArgsConstructor
public class AttendanceUserController {

    private final AttendanceUserService userService;

    @PostMapping("/checkIn")
    @Operation(summary = "출근", description = "출근")
    public ResponseEntity<CheckInResponseDto> checkIn(@RequestBody CheckInRequestDto dto){

        CheckInResponseDto attendance = userService.checkIn(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(attendance);
    }

    @PostMapping("/checkOut")
    @Operation(summary = "퇴근", description = "퇴근")
    public ResponseEntity<CheckInResponseDto> checkOut(@RequestBody CheckInRequestDto dto){

        CheckInResponseDto attendance = userService.checkOut(dto);
        return ResponseEntity.ok().body(attendance);
    }
}