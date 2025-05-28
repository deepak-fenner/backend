package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.OtpRequest;
import com.example.demo.service.OtpService;

@RestController
@CrossOrigin(origins = "*") 
public class OtpController {

    @Autowired
    private OtpService otpService;

    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOtp(@RequestBody OtpRequest request) {
        otpService.sendOtp(request.getEmail());
        return ResponseEntity.ok("OTP sent");
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestBody OtpRequest request) {
        boolean valid = otpService.verifyOtp(request.getEmail(), request.getOtp());
        return valid
            ? ResponseEntity.ok("OTP verified")
            : ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid OTP");
    }
}

