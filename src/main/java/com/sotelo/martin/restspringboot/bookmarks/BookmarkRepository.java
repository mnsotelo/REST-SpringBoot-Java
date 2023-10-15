package com.sotelo.martin.restspringboot.bookmarks;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    @Query("""
        SELECT new com.sotelo.martin.restspringboot.bookmarks.BookmarkDTO(b.id, b.title, b.url, b.createdAt)
        FROM Bookmark b
            """)
    Page<BookmarkDTO> findBookMarks(Pageable pageable);
}