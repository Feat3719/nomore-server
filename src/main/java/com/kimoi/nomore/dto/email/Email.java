package com.kimoi.nomore.dto.email;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Email {

    private String to;
    private String subject;
    private String message;
    
}
