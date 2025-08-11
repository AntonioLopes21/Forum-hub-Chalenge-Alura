package com.adepadua.forumhub.forumhub.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "respostas")
@Getter
@Setter
@RequiredArgsConstructor
public class Resposta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(min = 10, max = 500, message = "A mensagem deve ter entre 10 e 500 caracteres.")
    private String mensagem;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "topico_id")
    private Topico topico;

    @NotNull
    private LocalDate dataCriacao = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "autor")
    private Usuario autor;

    private Boolean solucao;
}
