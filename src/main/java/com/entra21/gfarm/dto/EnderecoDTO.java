package com.entra21.gfarm.dto;

import com.entra21.gfarm.model.Endereco;
import lombok.Data;

@Data
public class EnderecoDTO {

  private Long id;
  private String logradouro;
  private int numero;
  private String bairro;
  private String cidade;

  public static EnderecoDTO fromEntity(Endereco endereco) {
    if (endereco == null) {
      return null;
    }

    EnderecoDTO enderecoDTO = new EnderecoDTO();
    enderecoDTO.setId(endereco.getId());
    enderecoDTO.setLogradouro(endereco.getLogradouro());
    enderecoDTO.setNumero(endereco.getNumero());
    enderecoDTO.setBairro(endereco.getBairro());
    enderecoDTO.setCidade(endereco.getCidade());

    return enderecoDTO;
  }
}
