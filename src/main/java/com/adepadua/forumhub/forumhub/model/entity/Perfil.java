package com.adepadua.forumhub.forumhub.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "perfis")
@Getter
@Setter
@RequiredArgsConstructor
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "O nome do perfil não pode ser nulo ou vazio.")
    private String nome;

    @ManyToMany(mappedBy = "perfis")
    private List<Usuario> usuarios;
}
