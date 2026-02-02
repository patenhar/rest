package com.something.rest.controllers;

import com.something.rest.utils.ApiResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    @PostMapping("/login")
        public ResponseEntity<ApiResponse<String>> login(HttpServletResponse response) {
            Cookie cookie = new Cookie("Token", "Something");
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            response.addCookie(cookie);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("Success", null));
        }
}
