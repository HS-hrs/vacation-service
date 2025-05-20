package HS_hrs.vacation_service.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import HS_hrs.vacation_service.Entity.Vacation;

public interface VacationRepository extends JpaRepository<Vacation, Long>{

    List<Vacation> findByUserId(Integer userId);
}