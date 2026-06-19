package com.heavylink.controller;

import com.heavylink.Security.JwtRequest;
import com.heavylink.Security.JwtTokenUtil;
import com.heavylink.Security.JwtUserDetailsService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
@RequiredArgsConstructor
@Slf4j
// Habilita el intercambio de recursos de origen cruzado (CORS) y permite el envío de cookies
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserDetailsService jwtUserDetailsService;

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody JwtRequest jwtRequest, HttpServletResponse response) throws Exception {
        try {
            authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());

            final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
            final String accessToken = jwtTokenUtil.generateToken(userDetails);

            // Configuración óptima de cookies para desarrollo local sobre HTTP
            ResponseCookie cookie = ResponseCookie.from("jwt", accessToken)
                    .httpOnly(true)
                    .secure(false)    // Permite que la cookie viaje por HTTP sin requerir HTTPS en localhost
                    .path("/")
                    .sameSite("Lax")  // Evita el rechazo de cookies por políticas de navegación local
                    .maxAge(Duration.ofHours(5))
                    .build();

            response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());

            return ResponseEntity.ok(true);
        } catch(Exception e) {
            log.error("Login failed for user '{}': {}", jwtRequest.getUsername(), e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/auth/logout")
    public ResponseEntity<Void> logout(HttpServletResponse response) {
        // Limpieza de la cookie de autenticación al cerrar sesión
        ResponseCookie cookie = ResponseCookie.from("jwt", "")
                .httpOnly(true)
                .secure(false)
                .path("/")
                .sameSite("Lax")
                .maxAge(0) // Expira la cookie inmediatamente
                .build();

        response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return ResponseEntity.ok().build();
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}