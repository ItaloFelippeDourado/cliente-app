package com.nativeblood.clientes.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
//ANOTATION DA BIBLIOTECA LOMBOK RESPONSÁVEL POR CRIAR OS GETTERS & SETTERS DA ENTIDADE
@Data
//ANOTATION DA BIBLIOTECA LOMBOK RESPONSÁVEL POR CRIAR  O CONSTRUTOR SEM ARGUMENTOS
@NoArgsConstructor
//ANOTATION DA BIBLIOTECA LOMBOK RESPONSÁVEL POR CRIAR O CONSTRUTOR COM TODOS OS ARGUMENTOS
@AllArgsConstructor
@Builder
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 150)
    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String nome_cliente;

    @Column(nullable = false, length = 11)
    @NotNull(message = "{campo.cpf.obrigatorio}")
    @CPF(message = "{campo.cpf.invalido}")
    private String cpf_cliente;

    @Column(name = "data_cadastro_cliente", updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data_cadastro_cliente;

    @PrePersist
    public void prePersist() {
        setData_cadastro_cliente(LocalDate.now());
    }

}
