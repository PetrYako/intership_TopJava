package ru.javawebinar.topjava.repository.jdbc.date;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
@Profile("hsqldb")
public class DateForHSQL implements Date {

    @Override
    public Object getDate(LocalDateTime localDateTime) {
        return Timestamp.valueOf(localDateTime);
    }
}
