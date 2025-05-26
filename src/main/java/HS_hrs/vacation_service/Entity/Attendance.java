package HS_hrs.vacation_service.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import HS_hrs.vacation_service.Entity.Enum.AttendanceStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "attendance")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Attendance {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long Id;

  @Column(name = "user_id", nullable = false)
  private Integer userId; // users의 PK 참조

  @Column(nullable = false)
  private LocalDate date;

  @Column(name = "check_in_time")
  private LocalTime checkInTime;

  @Column(name = "check_out_time")
  private LocalTime checkOutTime;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private AttendanceStatus status;
  // NORMAL, LATE, EARLY_LEAVE, LEAVE ,ABSENT


  public static Attendance checkIn(Integer userId) {
    LocalDate today = LocalDate.now();
    LocalTime now = LocalTime.now();
    LocalTime limit = LocalTime.of(9, 0);

    AttendanceStatus attendanceStatus =
        now.isAfter(limit) ? AttendanceStatus.LATE : AttendanceStatus.NORMAL;

    return HS_hrs.vacation_service.Entity.Attendance.builder()
        .userId(userId)
        .date(today)
        .checkInTime(now)
        .status(attendanceStatus)
        .build();
  }

  public void checkOut(Integer userId) {
    LocalTime now = LocalTime.now();
    LocalTime limit = LocalTime.of(18, 0);

    AttendanceStatus attendanceStatus =
        now.isBefore(limit) ? AttendanceStatus.EARLY_LEAVE : AttendanceStatus.LEAVE;

    this.checkOutTime = now;
    this.status = attendanceStatus;
  }

}