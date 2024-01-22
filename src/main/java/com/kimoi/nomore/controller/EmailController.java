package com.kimoi.nomore.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kimoi.nomore.dto.EmailDto.Email;
import com.kimoi.nomore.dto.EmailDto.EmailPostRequest;
import com.kimoi.nomore.dto.EmailDto.EmailViewResponse;
import com.kimoi.nomore.dto.UserDto.FindUserPwdRequest;
import com.kimoi.nomore.service.AuthService;
import com.kimoi.nomore.service.EmailService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/email")
public class EmailController {

    private final EmailService emailService;
    private final AuthService authService;

    // 회원가입 인증 코드 메일 전송(회원가입)
    @PostMapping("/auth-signup")
    public ResponseEntity<?> sendJoinMail(@RequestBody EmailPostRequest request) {
        Email email = Email.builder()
                .to(request.getUserEmail())
                .subject("[Nomore] 회원가입 인증을 위한 인증 코드 발송 메일입니다.")
                .build();

        String code = emailService.sendMail(email, "email");

        EmailViewResponse emailViewResponse = new EmailViewResponse();
        emailViewResponse.setCode(code);

        return ResponseEntity.ok(emailViewResponse);
    }

    // 아이디 찾기(메일 전송)
    @GetMapping("/userid")
    public ResponseEntity<?> findUserId(@RequestBody EmailPostRequest emailPostRequest) {

        Email email = Email.builder()
                .to(emailPostRequest.getUserEmail())
                .subject("[Nomore] 아이디 찾기 인증 메일입니다.")
                .build();

        emailService.sendAuthEmail(emailPostRequest, email, "idfind");

        return ResponseEntity.status(HttpStatus.OK).body("SUCCESS : ID Found Done");
    }

    // 비밀번호 찾기(임시 비밀번호 발급 메일 전송)
    @GetMapping("/userpwd")
    public ResponseEntity<?> sendPasswordMail(@RequestBody FindUserPwdRequest findUserPwdRequest) {
        
        Email email = Email.builder()
                .to(authService.findUserPwd(findUserPwdRequest).getUserEmail())
                .subject("[Nomore] 임시 비밀번호 발급 메일입니다.")
                .build();

        emailService.sendMail(email, "password");

        return ResponseEntity.status(HttpStatus.OK).body("SUCCESS : Initialize and reissue password completed");
    }
    
}
