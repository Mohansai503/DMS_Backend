package com.dms.dmsproject.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dms.dmsproject.model.UserRegistration;
import com.dms.dmsproject.security.JwtUtil;
import com.dms.dmsproject.service.LoginServices;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/loginpage")
public class LoginController {

    @Autowired
    private LoginServices loginservices;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> loginSave(@RequestBody UserRegistration usreg) {

        UserRegistration existingUser =
                loginservices.findByEmail(usreg.getUserEmailId());

        if (existingUser == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Email not registered! Please register first.");
        }

        String token = jwtUtil.generateToken(existingUser.getUserEmailId());

        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("token", token);

        return ResponseEntity.ok(response);
    
    }

}
