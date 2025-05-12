package HS_hrs.vacation_service.Dto;

import java.time.LocalDate;

import HS_hrs.vacation_service.Entity.Enum.VacationType;

public class VacationRequestDto {

  private Integer userId;
  private Integer usedDayCount;
  private LocalDate startDate;
  private LocalDate endDate;
  private VacationType vacationType;
  private String reason;
  
}
