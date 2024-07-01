package com.example.demo.Auth;

// import org.springframework.web.bind.annotation.RestController;
// import com.example.demo.Service.PasswordEncryptionService;
// import com.example.demo.Usuario.Usuario;
// import com.example.demo.Usuario.UsuarioService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;

// @RestController
// @RequestMapping("/api/auth")
public class AuthController {

    // @Autowired
    // private AuthenticationManager authenticationManager;

    // @Autowired
    // private JwtTokenUtil jwtTokenUtil;

    // @Autowired
    // private UsuarioService usuarioService;

    // @Autowired
    // private PasswordEncoder passwordEncoder;

    // @PostMapping("/login")
    // public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

    //     Authentication authentication = authenticationManager.authenticate(
    //             new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

    //     SecurityContextHolder.getContext().setAuthentication(authentication);

    //     Usuario usuario = usuarioService.getByEmail(loginRequest.getEmail());

    //     if (passwordEncoder.matches(loginRequest.getPassword(), usuario.getPassword())) {
    //         String token = jwtTokenUtil.generateToken(authentication);
    //         return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    //     } else {
    //         return new ResponseEntity<>("Senha invalida", HttpStatus.NOT_ACCEPTABLE);
    //     }
        
    // }
}