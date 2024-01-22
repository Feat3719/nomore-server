package com.kimoi.nomore.dto;

import lombok.Getter;
import lombok.Setter;

public class EmailDto {

    @Getter
    @Setter
    @lombok.Builder
    public static class Email {

        private String to;
        private String subject;
        private String message;

    }

    @Getter
    public class EmailPostRequest {
        private String userEmail;
    }

    @Getter
    @Setter
    public static class EmailViewResponse {
        private String code;
    }

}
