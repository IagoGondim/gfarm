package com.entra21.gfarm.usuario;

public record RegisterDTO(String email, String password, UsuarioRole role) {
}