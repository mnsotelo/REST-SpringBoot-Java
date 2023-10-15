package com.sotelo.martin.restspringboot.bookmarks;

import java.net.URI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/bookmarks")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    public BookmarkController(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

    @GetMapping
    PagedResult<BookmarkDTO> findBookmarks(
        @RequestParam(name = "page", defaultValue = "1") Integer pageNumber,
        @RequestParam(name = "size", defaultValue = "10") Integer pageSize) {
        FindBookmarksQuery query = new FindBookmarksQuery(pageNumber, pageSize);
        return bookmarkService.findBookmarks(query);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<BookmarkDTO> create(@RequestBody @Validated CreateBookmarkRequest bookmarkRequest){
        CreateBookmarkCommand command = new CreateBookmarkCommand(bookmarkRequest.title(), bookmarkRequest.url());
        BookmarkDTO bookmark = bookmarkService.create(command);
        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/api/bookmarks/{id}")
            .buildAndExpand(bookmark.id()).toUri();
        return ResponseEntity.created(location).body(bookmark);    }
}
