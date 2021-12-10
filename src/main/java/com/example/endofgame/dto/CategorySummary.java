package com.example.endofgame.dto;

import java.time.LocalDateTime;

public record CategorySummary(Long id, String name,
                              LocalDateTime created,
                              LocalDateTime updated) {
}
