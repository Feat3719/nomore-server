package com.kimoi.nomore.dto.exception;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ErrorMessageResponse {
    private String message;
}
