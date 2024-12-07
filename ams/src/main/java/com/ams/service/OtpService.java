package com.ams.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ams.repository.UserRepository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Random;

@Service
public class OtpService {

    @Autowired
    private MailService mailService;

    @Autowired
    private UserRepository userRepository;

    private Map<String, String> otpCache = new ConcurrentHashMap<>();

    public boolean sendOtp(String email) {
        if (!userRepository.existsByEmail(email)) {
            System.out.println("Email not found: " + email);
            return false;
        }
        String otp = generateOtp(email);
        String message = "Your OTP is: " + otp;
        System.out.println("Generated OTP: " + otp + " for email: " + email);
        return mailService.sendEmail(email, message, "Your OTP Code");
    }

    public boolean verifyOtp(String email, String otp) {
        String cachedOtp = otpCache.get(email);
        System.out.println("Email: " + email + ", Entered OTP: " + otp + ", Cached OTP: " + cachedOtp);
        return otp != null && otp.equals(cachedOtp);
    }

    private String generateOtp(String email) {
        String otp = String.format("%06d", new Random().nextInt(999999));
        otpCache.put(email, otp);
        return otp;
    }
}
