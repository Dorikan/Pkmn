package su.drkn.pkmn.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.*;
import su.drkn.pkmn.models.dtos.UserDTO;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import java.util.List;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserDetailsManager userDetailsManager;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDTO userDTO) {
        // Шифруем пароль
        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());

        // Создаем нового пользователя
        User user = new User(userDTO.getUsername(), encodedPassword, List.of(new SimpleGrantedAuthority("ROLE_USER")));
        userDetailsManager.createUser(user);

        return ResponseEntity.ok("User registered successfully");
    }
}