package HS_hrs.vacation_service.Service;

import HS_hrs.vacation_service.Dto.DateCalculatorResult;
import HS_hrs.vacation_service.Dto.MyVacationListDto;
import HS_hrs.vacation_service.Dto.VacationDetailDto;
import HS_hrs.vacation_service.Dto.VacationRequestDto;
import HS_hrs.vacation_service.Dto.VacationUpdateDto;
import HS_hrs.vacation_service.Entity.Vacation;
import HS_hrs.vacation_service.Repository.VacationRepository;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VacationService {

    private final VacationRepository vacationRepository;

    @Transactional
    public void RequestLeave(VacationRequestDto dto) {

        DateCalculatorResult result = dateCalculator(dto.getStartDate(),dto.getEndDate(),dto.getUserId());

        Vacation vacation = Vacation.create(
            dto.getUserId(),
            dto.getStartDate(),
            dto.getEndDate(),
            dto.getVacationType(),
            dto.getReason(),
            result.getCurrentUsedDays(),
            result.getRemainingDays()
        );

        vacationRepository.save(vacation);
    }

    /*
    내 휴가 상세 조회
     */
    public VacationDetailDto getVacationDetail(Long id) {

        Vacation vacation = vacationRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("휴가 없음"));

        return new VacationDetailDto(vacation);
    }

    /*
    내 휴가 업데이트
     */
    public VacationDetailDto updateVacationDetail(Long id, VacationUpdateDto updateDto) {
        Vacation vacation = vacationRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("휴가 정보 없습니다."));

        DateCalculatorResult result = dateCalculator(updateDto.getStartDate(),updateDto.getEndDate(),updateDto.getUserId());

        vacation.updateDetail(updateDto.getReason(), updateDto.getStartDate(),
                              updateDto.getEndDate(), updateDto.getVacationType(), result.getCurrentUsedDays(),
                              result.getRemainingDays());

        vacationRepository.save(vacation);
        return new VacationDetailDto(vacation);
    }

    /*
    내 휴가 취소
     */
    public Long cancelVacation(Long id) {

        Vacation vacation = vacationRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("휴가 정보 없습니다."));

        vacationRepository.deleteById(id);
        return id;
    }

    public List<MyVacationListDto> getMyVacationList(Integer userId) {
        List<Vacation> myVacation = vacationRepository.findByUserId(userId);
      return myVacation.stream()
          .map(MyVacationListDto::fromEntity)
          .collect(Collectors.toList());
    }

    /*
     연차 계산기
     */
    private DateCalculatorResult dateCalculator(LocalDate startDate, LocalDate endDate, Integer userId) {

        int currentUsedDays = (int) ChronoUnit.DAYS.between(startDate, endDate) + 1;
        int totalUsedDays = vacationRepository.findByUserId(userId).stream()
            .mapToInt(Vacation::getUsedDayCount).sum();

        return new DateCalculatorResult(totalUsedDays, currentUsedDays);
    }
}