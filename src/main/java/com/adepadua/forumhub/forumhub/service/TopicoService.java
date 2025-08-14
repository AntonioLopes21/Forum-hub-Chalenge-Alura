package com.adepadua.forumhub.forumhub.service;

import com.adepadua.forumhub.forumhub.model.dto.CriarTopicoDTO;
import com.adepadua.forumhub.forumhub.model.dto.EditarTopicoDTO;
import com.adepadua.forumhub.forumhub.model.dto.ExibirTopicoDTO;
import com.adepadua.forumhub.forumhub.model.entity.Curso;
import com.adepadua.forumhub.forumhub.model.entity.Topico;
import com.adepadua.forumhub.forumhub.model.entity.Usuario;
import com.adepadua.forumhub.forumhub.repository.CursoRepository;
import com.adepadua.forumhub.forumhub.repository.TopicoRepository;
import com.adepadua.forumhub.forumhub.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<ExibirTopicoDTO> listarTopicos() {
        return topicoRepository.findAll()
                .stream()
                .map(topicos -> {
                    ExibirTopicoDTO dto = new ExibirTopicoDTO(topicos.getTitulo(), topicos.getMensagem(), topicos.getDataCriacao(), topicos.getStatus(), topicos.getAutor(), topicos.getCurso());
                    return dto;
                }).toList();
    }

    public ResponseEntity<Void> excluirTopicoPeloId(Long id) {
        if(topicoRepository.findById(id).isPresent()) {
            topicoRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    public ExibirTopicoDTO exibirTopicoPorId(Long id) {
        Topico topico = topicoRepository.findById(id).orElseThrow(() -> new RuntimeException("Tópico não encontrado com ID: " + id));
        return topico.listarEspecifico();
    }

    public ExibirTopicoDTO editarTopico(Long id, EditarTopicoDTO dto) {
        Topico topico = topicoRepository.findById(id).orElseThrow(() -> new RuntimeException("Tópico não encontrado com ID: " + id));

        boolean existeDuplicado = topicoRepository.existsByTituloAndMensagemAndIdNot(dto.titulo(), dto.mensagem(), id);
        if (existeDuplicado) {
            throw new RuntimeException("Tópico com mesmo título e mensagem já existe.");
        }

        if (dto.titulo() != null && !dto.titulo().isBlank()) {
            topico.setTitulo(dto.titulo());
        }
        if (dto.mensagem() != null && !dto.mensagem().isBlank()) {
            topico.setMensagem(dto.mensagem());
        }
        topicoRepository.save(topico);

        return topico.listarEspecifico();
    }

}
