package HS_hrs.vacation_service.Repository.Attendance;

import HS_hrs.vacation_service.Entity.Attendance;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long>, AttendanceRepositoryCustom {

   Optional<Attendance> findByUserId(Integer userId);
}