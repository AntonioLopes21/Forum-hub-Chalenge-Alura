package com.adepadua.forumhub.forumhub.service;

import com.adepadua.forumhub.forumhub.model.dto.CriarTopicoDTO;
import com.adepadua.forumhub.forumhub.model.entity.Curso;
import com.adepadua.forumhub.forumhub.model.entity.Topico;
import com.adepadua.forumhub.forumhub.model.entity.Usuario;
import com.adepadua.forumhub.forumhub.repository.CursoRepository;
import com.adepadua.forumhub.forumhub.repository.TopicoRepository;
import com.adepadua.forumhub.forumhub.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TopicoService {
    private final TopicoRepository topicoRepository;
    private final CursoRepository cursoRepository;
    private final UsuarioRepository usuarioRepository;

    public CriarTopicoDTO criarTopico(CriarTopicoDTO dto) {
        boolean existe = topicoRepository.existsByTituloAndMensagem(dto.titulo(), dto.mensagem());
        if (existe) {
            throw new RuntimeException("Tópico com mesmo título e mensagem já existe.");
        }

        Usuario autor = usuarioRepository.findByNome(dto.nomeAutor())
                .orElseThrow(() -> new RuntimeException("Autor não encontrado"));

        Curso curso = cursoRepository.findById(dto.cursoId())
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        Topico novoTopico = new Topico(dto.titulo(), dto.mensagem(), autor, curso);
        topicoRepository.save(novoTopico);

        return new CriarTopicoDTO(novoTopico.getTitulo(), novoTopico.getMensagem(), novoTopico.getAutor().getNome(), novoTopico.getCurso().getId()
        );
    }
}
