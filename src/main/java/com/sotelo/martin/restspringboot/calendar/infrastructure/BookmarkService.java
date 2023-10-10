package com.sotelo.martin.restspringboot.calendar.infrastructure;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;

    BookmarkService(BookmarkRepository calendarEventRepository) {
        this.bookmarkRepository = calendarEventRepository;
    }
}
