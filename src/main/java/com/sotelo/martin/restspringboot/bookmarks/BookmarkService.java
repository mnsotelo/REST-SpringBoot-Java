package com.sotelo.martin.restspringboot.bookmarks;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;

    BookmarkService(BookmarkRepository calendarEventRepository) {
        this.bookmarkRepository = calendarEventRepository;
    }

    public PagedResult<BookmarkDTO> findBookmarks(FindBookmarksQuery query){
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        int pageNumber = query.pageNumber() > 0 ? query.pageNumber() - 1 : 0;

        Pageable pageable = PageRequest.of(pageNumber, query.pageSize(), sort);
        Page<BookmarkDTO> page = bookmarkRepository.findBookMarks(pageable);


        return new PagedResult<>(
            page.getContent(),
            page.getTotalElements(),
            page.getNumber() + 1, // for user page number starts from 1
            page.getTotalPages(),
            page.isFirst(),
            page.isLast(),
            page.hasNext(),
            page.hasPrevious()
        );
    }
}
