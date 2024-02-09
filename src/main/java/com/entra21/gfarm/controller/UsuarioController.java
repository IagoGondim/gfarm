package com.entra21.gfarm.controller;

import com.entra21.gfarm.model.Usuario;
import com.entra21.gfarm.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

  @Autowired
  private UsuarioService usuarioService;

  @GetMapping
  public ResponseEntity<Set<Usuario>> listarUsuarios() {
    Set<Usuario> usuarios = usuarioService.listarUsuarios();
    return ResponseEntity.ok(usuarios);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Usuario> obterUsuarioPorId(@PathVariable Long id) {
    Usuario usuario = usuarioService.obterUsuarioPorId(id);
    return ResponseEntity.ok(usuario);
  }

  @CrossOrigin(origins = "http://127.0.0.1:5500")
  @PostMapping
  public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
    Usuario novoUsuario = usuarioService.criarUsuario(usuario);
    return ResponseEntity.ok().body(novoUsuario);
  }

}
