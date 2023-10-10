package com.sotelo.martin.restspringboot.calendar;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

interface BookmarkRepository extends JpaRepository<BookMark, Long> {
    @Query("""
        SELECT new com.sotelo.martin.restspringboot.calendar.BookmarkDTO(b.id, b.title, b.url, b.createdAt)
        FROM BookMark b
            """)
    Page<BookmarkDTO> findBookMarks(Pageable pageable);
}