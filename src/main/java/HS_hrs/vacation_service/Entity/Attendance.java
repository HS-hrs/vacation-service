package HS_hrs.vacation_service.Entity;

import java.time.LocalDate;
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
import lombok.Getter;

@Getter
@Entity
@Table(name = "attendance")
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
  // NORMAL, LATE, EARLY_LEAVE, ABSENT 
}