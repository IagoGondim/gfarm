package com.entra21.gfarm.usuario;

import com.entra21.gfarm.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private UsuarioRepository usuarioRepository;
  @Autowired
  private TokenService tokenService;
  @CrossOrigin(origins = "http://127.0.0.1:5500")
  @PostMapping("/login")
  public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
    var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
    var auth = this.authenticationManager.authenticate(usernamePassword);

    var token = tokenService.generateToken((Usuario) auth.getPrincipal());

    return ResponseEntity.ok(new LoginResponseDTO(token));
  }
  @CrossOrigin(origins = "http://127.0.0.1:5500")
  @PostMapping("/register")
  public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
    if(this.usuarioRepository.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();

    String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
    Usuario newUser = new Usuario(data.nome(), data.cpf(), data.email(), encryptedPassword, data.role());

    this.usuarioRepository.save(newUser);

    return ResponseEntity.ok().body(newUser);
  }
}
