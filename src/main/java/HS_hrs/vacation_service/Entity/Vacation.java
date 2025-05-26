package HS_hrs.vacation_service.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import HS_hrs.vacation_service.Entity.Enum.VacationStatus;
import HS_hrs.vacation_service.Entity.Enum.VacationType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vacations")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vacation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "user_id", nullable = false)
  private Integer userId;

  @Column(name = "left_day_count", nullable = false, columnDefinition = "INTEGER DEFAULT 12")
  private Integer leftDayCount;

  @Column(name = "used_day_count", nullable = false)
  private Integer usedDayCount;

  @Column(name = "start_date", nullable = false)
  private LocalDate startDate;

  @Column(name = "end_date", nullable = false)
  private LocalDate endDate;

  @Enumerated(EnumType.STRING)
  @Column(name = "vacation_type", nullable = false)
  private VacationType vacationType;
    // ANNUAL, SICK, UNPAID, OTHER

  @Column
  private String reason;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, name = "vacation_status")
  private VacationStatus status;
  // PENDING, APPROVED, REJECTED
    
  @Column(name = "created_at", nullable = false)
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @Column(name = "admin_updated_at")
  private LocalDateTime adminUpdateAt;



  @PrePersist // save 호출 시 자동 실행(DB에 저장하기 전에 실행)
  public void prePersist() {
    this.createdAt = LocalDateTime.now();
  }


  public static Vacation create(Integer userId, LocalDate startDate, LocalDate endDate, VacationType type, String reason, Integer usedDayCount, Integer leftDayCount) {
    if (startDate.isAfter(endDate)) {
        throw new IllegalArgumentException("시작일은 종료일보다 늦을 수 없습니다.");
      }


            return Vacation.builder()
                .userId(userId)
                .startDate(startDate)
                .endDate(endDate)
                .vacationType(type)
                .reason(reason)
                .status(VacationStatus.PENDING)
                .usedDayCount(usedDayCount)
                .leftDayCount(leftDayCount)
                .build();
  }

  public void updateDetail(String reason, LocalDate startDate, LocalDate endDate, VacationType vacationType, Integer usedDayCount, Integer leftDayCount){

    if (startDate.isAfter(endDate)) {
      throw new IllegalArgumentException("시작일은 종료일보다 늦을 수 없습니다.");
    }

    this.reason = reason;
    this.startDate = startDate;
    this.endDate = endDate;
    this.vacationType = vacationType;
    this.usedDayCount = usedDayCount;
    this.leftDayCount = leftDayCount;
    this.updatedAt = LocalDateTime.now();
  }

  public void StatusUpdate(VacationStatus status) {
    this.status = status;
    this.adminUpdateAt = LocalDateTime.now();
  }
}