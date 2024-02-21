package com.entra21.gfarm.dto;

import com.entra21.gfarm.model.Fazenda;
import com.entra21.gfarm.model.Funcionario;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class FuncionarioDTO {
  private Long id;
  private String nome;
  private String cargo;
  private Integer salario;
  private Timestamp dataContratacao;
  private Long fazendaId;
  private List<Long> atividadesIds;

  public static FuncionarioDTO fromEntity(Funcionario funcionario) {
    if (funcionario == null) {
      return null;
    }

    FuncionarioDTO dto = new FuncionarioDTO();
    dto.setId(funcionario.getId());
    dto.setNome(funcionario.getNome());
    dto.setCargo(funcionario.getCargo());
    dto.setSalario(funcionario.getSalario());
    dto.setDataContratacao(funcionario.getDataContratacao());
    if (funcionario.getFazenda() != null) {
      dto.setFazendaId(funcionario.getFazenda().getId());
    }
    if (funcionario.getAtividadesAgricolas() != null) {
      dto.setAtividadesIds(funcionario.getAtividadesAgricolas().stream()
              .map(atividade -> atividade != null ? atividade.getId() : null)
              .collect(Collectors.toList()));
    }
    return dto;
  }


  public static Funcionario toEntity(FuncionarioDTO funcionarioDTO) {
    if (funcionarioDTO == null) {
      return null;
    }

    Funcionario funcionario = new Funcionario();
    funcionario.setId(funcionarioDTO.getId());
    funcionario.setNome(funcionarioDTO.getNome());
    funcionario.setCargo(funcionarioDTO.getCargo());
    funcionario.setSalario(funcionarioDTO.getSalario());
    funcionario.setDataContratacao(funcionarioDTO.getDataContratacao());

    if (funcionarioDTO.getFazendaId() != null) {
      Fazenda fazenda = new Fazenda();
      fazenda.setId(funcionarioDTO.getFazendaId());
      funcionario.setFazenda(fazenda);
    }

    return funcionario;
  }
}
