package com.sotelo.martin.restspringboot.calendar.infrastructure;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CalendarEventService {
    private final CalendarEventRepository calendarEventRepository;

     CalendarEventService(CalendarEventRepository calendarEventRepository) {
        this.calendarEventRepository = calendarEventRepository;
    }
}
