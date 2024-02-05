package com.entra21.gfarm.fazenda;

import com.entra21.gfarm.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_fazenda")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Fazenda {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String nome;

  @Column
  private int areaTotal;

  @ManyToOne
  @JoinColumn(name = "usuario_id", referencedColumnName = "id")
  private Usuario usuario;

//  @OneToOne(cascade = CascadeType.ALL)
//  @JoinColumn(name = "endereco_id", referencedColumnName = "id")
//  private Endereco endereco;
}
