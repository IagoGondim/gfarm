package com.entra21.gfarm.dto;

import com.entra21.gfarm.enuns.UsuarioRole;

public record RegisterDTO(String nome, String cpf, String email, String password, UsuarioRole role) {
}