package su.drkn.pkmn.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import su.drkn.pkmn.models.dtos.UserDTO;
import su.drkn.pkmn.security.services.JwtService;

import javax.security.auth.login.CredentialException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @Value("${jwt.expiration-time:3600000}")
    private int cookieExpirationTime;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO) {
        try {
            // Аутентификация пользователя
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userDTO.getUsername(),
                            userDTO.getPassword()
                    )
            );

            String jwtToken = (String) authentication.getCredentials();
            System.out.println(jwtToken);
            return ResponseEntity.ok(jwtToken);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @PostMapping("/success")
    public ResponseEntity<String> handleLoginSuccess(HttpServletResponse response, Authentication authentication) {
        String username = authentication.getName();
        List<String> authorities = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        String jwtToken = jwtService.generateToken(username, authorities);

        String encodedToken = java.util.Base64.getEncoder().encodeToString(jwtToken.getBytes());

        Cookie jwtCookie = new Cookie("JWT_TOKEN", encodedToken);
        jwtCookie.setHttpOnly(true);
        jwtCookie.setSecure(true);
        jwtCookie.setPath("/");
        jwtCookie.setMaxAge(cookieExpirationTime);

        response.addCookie(jwtCookie);
        return ResponseEntity.ok(jwtToken);
    }
}
