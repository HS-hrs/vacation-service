package HS_hrs.vacation_service.Repository.Attendance;

import HS_hrs.vacation_service.Entity.Attendance;
import HS_hrs.vacation_service.Entity.Enum.AttendanceStatus;
import HS_hrs.vacation_service.Entity.QAttendance;
import HS_hrs.vacation_service.Entity.QVacation;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AttendanceRepositoryImpl implements AttendanceRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Attendance> search(Integer userId, AttendanceStatus status) {
        QAttendance a = QAttendance.attendance;
        BooleanBuilder builder = new BooleanBuilder();

        if(userId != null) {
            builder.and(a.userId.eq(userId));
        }
        if(status != null) {
            builder.and(a.status.eq(status));
        }



        return queryFactory.selectFrom(a).where(builder).fetch();
    }


}