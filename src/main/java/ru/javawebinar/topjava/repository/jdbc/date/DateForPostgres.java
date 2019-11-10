package ru.javawebinar.topjava.repository.jdbc.date;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Profile("postgres")
public class DateForPostgres implements Date {

    @Override
    public Object getDate(LocalDateTime localDateTime) {
        return localDateTime;
    }
}
