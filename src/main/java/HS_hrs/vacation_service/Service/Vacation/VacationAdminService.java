package HS_hrs.vacation_service.Service.Vacation;

import HS_hrs.vacation_service.Dto.Vacation.Admin.UserVacationDetailDto;
import HS_hrs.vacation_service.Dto.Vacation.Admin.VacationStatusUpdateDto;
import HS_hrs.vacation_service.Dto.Vacation.Admin.AllVacationListDto;
import HS_hrs.vacation_service.Entity.Enum.VacationStatus;
import HS_hrs.vacation_service.Entity.Enum.VacationType;
import HS_hrs.vacation_service.Entity.Vacation;
import HS_hrs.vacation_service.Repository.Vacation.VacationRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VacationAdminService {

    private final VacationRepository vacationRepository;

    /*
    모든 유저 휴가 목록 조회
     */
    public List<AllVacationListDto> AllList() {
        List<Vacation> userList = vacationRepository.findAll();

        return userList.stream().map(AllVacationListDto::fromEntity)
            .collect(Collectors.toList());

    }
    /*
    조건 휴가 검색
     */
    public List<AllVacationListDto> search(Integer userId, VacationType vacationType, VacationStatus vacationStatus) {

        List<Vacation> search = vacationRepository.search(userId, vacationType, vacationStatus);

        return search.stream().map(AllVacationListDto::fromEntity).collect(Collectors.toList());
    }

    /*
    상세 휴가 조회
     */
    public UserVacationDetailDto getUserVacationDetail(Integer userId, Long id) {

        Vacation vacation = vacationRepository.findByUserIdAndId(userId, id)
            .orElseThrow(() -> new EntityNotFoundException("해당 사용자의 휴가 정보가 없습니다."));

        return new UserVacationDetailDto(vacation);
    }

    /*
    휴가 승인, 취소
     */
    public UserVacationDetailDto statusUpdate(Integer userId, Long id, VacationStatusUpdateDto dto) {
        Vacation vacation = vacationRepository.findByUserIdAndId(userId, id)
            .orElseThrow(() -> new EntityNotFoundException("해당 사용자의 휴가 정보가 없습니다."));

        vacation.StatusUpdate(dto.getVacationStatus());

        vacationRepository.save(vacation);

        return new UserVacationDetailDto(vacation);
    }

}