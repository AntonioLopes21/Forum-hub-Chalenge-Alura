package com.adepadua.forumhub.forumhub.model.enums;

import lombok.Getter;

@Getter
public enum Categoria {
    REDES_DE_COMPUTADORES("Redes de Computadores"),
    ANALISE_E_DESENVOLVIMENTO_DE_SISTEMAS("Análise e Desenvolvimento de Sistemas"),
    ENGENHARIA_DE_SOFTWARE("Engenharia de Software"),
    CIENCIA_DA_COMPUTACAO("Ciência da Computação");

    private final String descricao;

    Categoria(String descricao) {
        this.descricao = descricao;
    }

    // Método para mapear string para enum
    public static Categoria fromString(String nome) {
        if (nome == null) return null;

        String nomeNormalizado = nome.trim().toLowerCase().replaceAll("[ _-]", "");

        for (Categoria categoria : Categoria.values()) {
            String descricaoNormalizada = categoria.descricao.toLowerCase().replaceAll("[ _-]", "");
            if (descricaoNormalizada.equals(nomeNormalizado)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Categoria desconhecida: " + nome);
    }
}
