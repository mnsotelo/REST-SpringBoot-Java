package com.sotelo.martin.restspringboot.bookmarks;

public record UpdateBookmarkCommand(Long id, String title, String url) {}
