package HS_hrs.vacation_service.Service.Vacation;

import HS_hrs.vacation_service.Dto.Vacation.User.DateCalculatorResult;
import HS_hrs.vacation_service.Dto.Vacation.User.MyVacationListDto;
import HS_hrs.vacation_service.Dto.Vacation.User.VacationDetailDto;
import HS_hrs.vacation_service.Dto.Vacation.User.VacationRequestDto;
import HS_hrs.vacation_service.Dto.Vacation.User.VacationSummaryResponse;
import HS_hrs.vacation_service.Dto.Vacation.User.VacationUpdateDto;
import HS_hrs.vacation_service.Entity.Vacation;
import HS_hrs.vacation_service.Repository.Vacation.VacationRepository;
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
public class VacationUserService {

    private final VacationRepository vacationRepository;


    /*
    휴가 등록
     */
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
   상세 휴가 조회
     */
    public VacationDetailDto getVacationDetail(Long id) {

        Vacation vacation = vacationRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("휴가 없음"));

        return new VacationDetailDto(vacation);
    }

    /*
    상세 휴가 업데이트
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
    휴가 취소
     */
    public Long cancelVacation(Long id) {

        Vacation vacation = vacationRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("휴가 정보 없습니다."));

        vacationRepository.deleteById(id);
        return id;
    }

    /*
    모든 휴가 목록 조회
     */
    public VacationSummaryResponse getMyVacationList(Integer userId) {
        List<Vacation> myVacation = vacationRepository.findByUserId(userId);

        List<MyVacationListDto> dtoList = myVacation.stream()
            .map(MyVacationListDto::fromEntity)
            .collect(Collectors.toList());

        int usedDays = myVacation.stream()
            .mapToInt(Vacation::getUsedDayCount)
            .sum();

        int totalAnnualLeave = 12; // 사용자별 연차 정책이 있다면 여기서 계산
        int remainingDays = totalAnnualLeave - usedDays;

        return new VacationSummaryResponse(dtoList,remainingDays,usedDays);
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