package ru.javawebinar.topjava.repository.jdbc.date;

import java.time.LocalDateTime;

public interface Date<T> {

    T getDate(LocalDateTime localDateTime);
}
