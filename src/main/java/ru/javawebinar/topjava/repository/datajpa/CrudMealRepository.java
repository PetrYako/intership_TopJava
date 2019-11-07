package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Meal m WHERE m.id=:id AND m.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Transactional
    @Modifying
    @Query("UPDATE Meal m SET m.description=:description, m.dateTime=:dateTime, m.calories=:calories WHERE m.id=:id AND m.user.id=:userId")
    int update(@Param("dateTime") LocalDateTime dateTime, @Param("description") String description, @Param("calories") int calories,@Param("userId") int userId, @Param("id") int id);

    @Query("SELECT m FROM Meal m WHERE m.id=:id AND m.user.id=:userId")
    Meal get(@Param("id") int id, @Param("userId") int userId);

    @Query("SELECT u FROM User u WHERE u.id=:userId")
    User getUser(@Param("userId") int userId);

    @Query("SELECT m FROM Meal m WHERE m.user.id=:userId AND m.dateTime >= :startDate AND m.dateTime < :endDate ORDER BY m.dateTime DESC")
    List<Meal> findByDate(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("userId") int userId);
}
