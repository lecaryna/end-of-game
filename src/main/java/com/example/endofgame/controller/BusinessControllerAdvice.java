package com.example.endofgame.controller;

import com.example.endofgame.dto.ErrorResponse;
import com.example.endofgame.exception.DeletingNonExistentObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;

@RestControllerAdvice
public class BusinessControllerAdvice {

    @ExceptionHandler(DeletingNonExistentObject.class)
    public ResponseEntity<ErrorResponse> handleNonExistentCategoryEntities(DeletingNonExistentObject exc) {
        ErrorResponse response = new ErrorResponse(
                LocalDateTime.now(),
                "You're do it wrong!!!",
                exc.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                ServletUriComponentsBuilder.fromCurrentRequest().build().getPath()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
