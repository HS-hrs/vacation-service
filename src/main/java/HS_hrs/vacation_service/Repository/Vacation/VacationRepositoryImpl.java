package HS_hrs.vacation_service.Repository.Vacation;

import static javax.management.Query.eq;

import HS_hrs.vacation_service.Entity.Enum.VacationStatus;
import HS_hrs.vacation_service.Entity.Enum.VacationType;
import HS_hrs.vacation_service.Entity.QVacation;
import HS_hrs.vacation_service.Entity.Vacation;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class VacationRepositoryImpl implements VacationRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Vacation> search(Integer userId, VacationType vacationType,
        VacationStatus vacationStatus) {
        QVacation v = QVacation.vacation;
        BooleanBuilder builder = new BooleanBuilder();

        if(userId != null) {
            builder.and(v.userId.eq(userId));
        }
        if(vacationStatus != null) {
            builder.and(v.status.eq(vacationStatus));
        }
        if(vacationType != null) {
            builder.and(v.vacationType.eq(vacationType));
        }



        return queryFactory.selectFrom(v).where(builder).fetch();
    }
}