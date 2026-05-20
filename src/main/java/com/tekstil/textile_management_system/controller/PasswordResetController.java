package com.tekstil.textile_management_system.controller;

import com.tekstil.textile_management_system.dto.BaseResponse;
import com.tekstil.textile_management_system.entity.User;
import com.tekstil.textile_management_system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class PasswordResetController {

    @Value("${support.email}")
    private String supportEmail;

    @Value("${app.frontend.url:http://localhost:5173}")
    private String frontendUrl;

    private final UserService userService;
    private final JavaMailSender mailSender;

    // 1. Adım: Email gönder
    @PostMapping("/api/user/resetPassword")
    public ResponseEntity<BaseResponse<Void>> resetPassword(@RequestParam("email") String userEmail) {

        User user = userService.findEntityByEmail(userEmail);

        String token = UUID.randomUUID().toString();
        userService.createPasswordResetTokenForUser(user, token);

        String url = frontendUrl + "/reset-password?token=" + token;
        String message = "Şifrenizi sıfırlamak için aşağıdaki linke tıklayın:\n" + url;

        SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject("Şifre Sıfırlama");
        email.setText(message);
        email.setTo(user.getEmail());
        email.setFrom(supportEmail);
        mailSender.send(email);

        return ResponseEntity.ok(
                BaseResponse.success(
                        HttpStatus.OK.value(),
                        "Şifre sıfırlama e-postası gönderildi.",
                        null
                )
        );
    }

    // 2. Adım: Token doğrula ve şifreyi güncelle
    @PostMapping("/api/user/changePassword")
    public ResponseEntity<BaseResponse<Void>> changePassword(
            @RequestParam("token") String token,
            @RequestParam("newPassword") String newPassword) {

        String result = userService.validatePasswordResetToken(token);

        if (result != null) {
            return ResponseEntity.badRequest().body(
                    BaseResponse.error(HttpStatus.BAD_REQUEST.value(), result)
            );
        }

        userService.changePasswordByToken(token, newPassword);

        return ResponseEntity.ok(
                BaseResponse.success(
                        HttpStatus.OK.value(),
                        "Şifre başarıyla güncellendi.",
                        null
                )
        );
    }
}