package com.adepadua.forumhub.forumhub.model.entity;

import com.adepadua.forumhub.forumhub.model.enums.Categoria;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cursos")
@Getter
@Setter
@RequiredArgsConstructor
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(min = 10, max = 500, message = "O nome deve ter entre 10 e 500 caracteres.")
    private String nome;

    @Enumerated(value = EnumType.STRING)
    private Categoria categoria;

    public Curso(String nome, Categoria categoria) {
        this.nome = nome;
        this.categoria = categoria;
    }
}
