package HS_hrs.vacation_service.Service;

import HS_hrs.vacation_service.Dto.VacationAdminDtoList.AllVacationListDto;
import HS_hrs.vacation_service.Entity.Enum.VacationStatus;
import HS_hrs.vacation_service.Entity.Enum.VacationType;
import HS_hrs.vacation_service.Entity.Vacation;
import HS_hrs.vacation_service.Repository.VacationRepository;
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

}