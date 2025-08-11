package com.adepadua.forumhub.forumhub.service;

import com.adepadua.forumhub.forumhub.model.dto.CriarTopicoDTO;
import com.adepadua.forumhub.forumhub.model.entity.Curso;
import com.adepadua.forumhub.forumhub.model.entity.Topico;
import com.adepadua.forumhub.forumhub.model.enums.Categoria;
import com.adepadua.forumhub.forumhub.repository.TopicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TopicoService {
    private final TopicoRepository topicoRepository;

    public CriarTopicoDTO criarTopico(Topico topico) {
        topicoRepository.save(topico);
        return new CriarTopicoDTO(topico.getTitulo(), topico.getMensagem(), topico.getAutor().getNome(), topico.getCurso().getId());
    }

}
