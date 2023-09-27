package com.sotelo.martin.restspringboot.calendar.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

interface CalendarEventRepository extends JpaRepository<CalendarEvent, Long> {
}