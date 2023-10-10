package com.sotelo.martin.restspringboot.calendar;

import java.time.Instant;

public record BookmarkDTO(
    Long id,
    String title,
    String url,
    Instant createdAt
) {}
