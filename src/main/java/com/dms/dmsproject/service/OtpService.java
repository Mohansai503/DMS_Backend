package com.dms.dmsproject.service;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dms.dmsproject.dao.OtpDao;
import com.dms.dmsproject.model.UserRegistration;

@Service
public class OtpService {

    @Autowired
    private OtpDao otpDao;
    
    @Autowired
    private EmailService emailService;

    // ===============================
    // GENERATE OTP FOR REGISTRATION
    // ===============================
    public String generateOtpForRegistration(String email, String userName) {

        if (otpDao.existsByUserEmailId(email)) {
            throw new RuntimeException("Email already registered");
        }

        String otp = generateOtp();

        UserRegistration user = new UserRegistration();
        user.setUserEmailId(email);
        user.setUserName(userName);
        user.setUserOtp(otp);
        user.setOtpExpiryTime(LocalDateTime.now().plusMinutes(5));
        user.setVerified(false);

        otpDao.save(user);

        emailService.sendOtpEmail(email, otp);

        return "OTP sent successfully";
    }

    // ===============================
    // GENERATE OTP FOR LOGIN
    // ===============================
    public String generateOtpForLogin(String email) {

        UserRegistration user = otpDao.findByUserEmailId(email);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        if (!user.isVerified()) {
            throw new RuntimeException("User not verified. Please register first.");
        }

        String otp = generateOtp();

        user.setUserOtp(otp);
        user.setOtpExpiryTime(LocalDateTime.now().plusMinutes(5));

        otpDao.save(user);

        // 🔥 Send real email
        emailService.sendOtpEmail(email, otp);

        return "Login OTP sent to your email";
    }

    // ===============================
    // PRIVATE OTP GENERATOR
    // ===============================
    private String generateOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }
}