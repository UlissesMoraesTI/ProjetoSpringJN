package com.mstech.projetospring.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "rua")
    private String rua;
    @Column(name = "numero")
    private Long numero;
    @Column(name = "complemento", length = 30)
    private String complemento;
    @Column(name = "cidade", length = 50)
    private String cidade;
    @Column(name = "estado", length = 30)
    private String estado;
    @Column(name = "uf", length = 2)
    private String uf;
    @Column(name = "cep", length = 9)
    private String cep;
    @Column(name = "tipo", length = 30)
    private String tipo;
}
