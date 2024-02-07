package com.entra21.gfarm.endereco;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDTO {
  private String logradouro;
  private int numero;
  private String bairro;
  private String cidade;

  public static EnderecoDTO fromEntity(Endereco endereco) {
    EnderecoDTO enderecoDTO = new EnderecoDTO();
    enderecoDTO.setLogradouro(endereco.getLogradouro());
    enderecoDTO.setNumero(endereco.getNumero());
    enderecoDTO.setBairro(endereco.getBairro());
    enderecoDTO.setCidade(endereco.getCidade());
    return enderecoDTO;
  }
}
