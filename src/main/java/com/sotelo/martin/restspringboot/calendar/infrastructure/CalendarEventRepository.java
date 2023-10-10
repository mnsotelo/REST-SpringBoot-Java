package com.sotelo.martin.restspringboot.calendar.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

interface BookmarkRepository extends JpaRepository<BookMark, Long> {
}