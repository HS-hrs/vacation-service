package HS_hrs.vacation_service.Dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

import HS_hrs.vacation_service.Entity.Enum.VacationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VacationRequestDto {

  /*
  휴가 등록 DTO
   */

  private Integer userId;
  @NotNull(message = "휴가 시작일은 필수입니다." )
  private LocalDate startDate;
  @NotNull(message = "휴가 종료일은 필수입니다." )
  private LocalDate endDate;
  @NotNull(message = "휴가 타입은 필수입니다.")
  private VacationType vacationType;
  @Size(max = 500, message = "사유는 500자를 초과할 수 없습니다")
  private String reason;

}