package com.kimoi.nomore.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kimoi.nomore.dto.UserDto.GetUserInfoResponse;
import com.kimoi.nomore.dto.UserDto.SaveUserInfoRequest;
import com.kimoi.nomore.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    // 유저 정보 조회
    @GetMapping("/info")
    public ResponseEntity<GetUserInfoResponse> getUserInfo(@RequestParam String userId) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserInfo(userId));
    }

    // 유저 정보 저장(수정)
    @PatchMapping("/modify")
    public ResponseEntity<?> patchUserInfo(@RequestBody SaveUserInfoRequest request) {
        try {
            userService.updateUserInfo(request);
            return ResponseEntity.ok("SUCCESS : UPDATE USER INFO");
        } catch (Exception ex) {
            // 로그 기록, 오류 처리 등
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR : " + ex.getMessage());
        }
    }
}
