package com.example.demo.controller;

import com.example.demo.config.SecurityConfig;
import com.example.demo.model.OtpRequest;
import com.example.demo.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping
@CrossOrigin(origins = "*")
public class OtpController {

    private final OtpService otpService;
    private final SecurityConfig securityConfig;

    @Autowired
    public OtpController(OtpService otpService, SecurityConfig securityConfig) {
        this.otpService = otpService;
        this.securityConfig = securityConfig;
    }

    @PostMapping("/send-otp")
    public ResponseEntity<Map<String, String>> sendOtp(@RequestBody OtpRequest request) {
        otpService.sendOtp(request.getEmail());
        System.out.println("✅ OTP sent to: " + request.getEmail());
        return ResponseEntity.ok(Map.of("message", "OTP sent successfully"));
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<Map<String, String>> verifyOtp(@RequestBody OtpRequest request) {
        boolean isValid = otpService.verifyOtp(request.getEmail(), request.getOtp());
        if (isValid) {
            return ResponseEntity.ok(Map.of("message", "OTP verified"));
        } else {
            return ResponseEntity.status(401).body(Map.of("error", "Invalid OTP"));
        }
    }
}
