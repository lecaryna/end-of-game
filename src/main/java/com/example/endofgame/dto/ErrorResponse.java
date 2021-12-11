package com.example.endofgame.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ErrorResponse(@JsonFormat(pattern = "'date:'dd-MM-yyyy 'time:'HH:mm:ss") LocalDateTime timestamp, String detailMessage, String genericMessage, int responseStatus, String path) {
}
