package com.adepadua.forumhub.forumhub.repository;

import com.adepadua.forumhub.forumhub.model.entity.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Optional<Topico> findByTitulo(String titulo);
    Optional<Topico> findByMensagem(String mensagem);

    boolean existsByTituloAndMensagem(String titulo, String mensagem);

    boolean existsByTituloAndMensagemAndIdNot(String titulo, String mensagem, Long id);
}
