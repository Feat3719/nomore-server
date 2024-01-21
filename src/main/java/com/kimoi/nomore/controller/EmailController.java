package com.kimoi.nomore.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kimoi.nomore.dto.email.Email;
import com.kimoi.nomore.dto.email.EmailPostRequest;
import com.kimoi.nomore.dto.email.EmailViewResponse;
import com.kimoi.nomore.service.EmailService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/email")
public class EmailController {
    
    private final EmailService emailService;

    // 임시 비밀번호 발급(비밀번호 찾기)
    @PostMapping("/pwd")
    public ResponseEntity<?> sendPasswordMail(@RequestBody EmailPostRequest request) {
        Email email = Email.builder()
                .to(request.getEmail())
                .subject("[Nomore] 임시 비밀번호 발급 메일입니다.")
                .build();

        emailService.sendMail(email, "password");

        return ResponseEntity.ok().build();
    }

    // 회원가입 인증 코드 메일 전송(회원가입)
    @PostMapping("/email")
    public ResponseEntity<?> sendJoinMail(@RequestBody EmailPostRequest request) {
        Email email = Email.builder()
                .to(request.getEmail())
                .subject("[SAVIEW] 이메일 인증을 위한 인증 코드 발송")
                .build();

        String code = emailService.sendMail(email, "email");

        EmailViewResponse emailViewResponse = new EmailViewResponse();
        emailViewResponse.setCode(code);

        return ResponseEntity.ok(emailViewResponse);
    }

}
