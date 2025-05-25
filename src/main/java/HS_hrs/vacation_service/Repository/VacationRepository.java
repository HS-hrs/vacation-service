package HS_hrs.vacation_service.Repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import HS_hrs.vacation_service.Entity.Vacation;

public interface VacationRepository extends JpaRepository<Vacation, Long>, VacationRepositoryCustom {

    List<Vacation> findByUserId(Integer userId);

    Optional<Vacation> findByUserIdAndId(Integer userId, Long id);
}