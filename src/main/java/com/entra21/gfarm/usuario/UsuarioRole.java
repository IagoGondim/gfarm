package com.entra21.gfarm.usuario;

public enum UsuarioRole {
  ADMIN("admin"),
  USUARIO("usuario");

  private String role;

  UsuarioRole(String role){
    this.role = role;
  }

  public String getRole(){
    return role;
  }
}
