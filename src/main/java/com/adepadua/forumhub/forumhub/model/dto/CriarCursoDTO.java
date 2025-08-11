package com.adepadua.forumhub.forumhub.model.dto;

import com.adepadua.forumhub.forumhub.model.entity.Curso;
import com.adepadua.forumhub.forumhub.model.enums.Categoria;

public record CriarCursoDTO(String nome, Categoria categoria) {
    public CriarCursoDTO(String nome, String categoriaStr) {
        this(nome, Categoria.fromString(categoriaStr));
    }
    public Curso toEntity() {
        return new Curso(this.nome, this.categoria);
    }
}
