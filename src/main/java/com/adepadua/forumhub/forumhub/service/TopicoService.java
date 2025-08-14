package com.adepadua.forumhub.forumhub.service;

import com.adepadua.forumhub.forumhub.model.dto.CriarTopicoDTO;
import com.adepadua.forumhub.forumhub.model.dto.ExibirTopicoDTO;
import com.adepadua.forumhub.forumhub.model.entity.Curso;
import com.adepadua.forumhub.forumhub.model.entity.Topico;
import com.adepadua.forumhub.forumhub.model.entity.Usuario;
import com.adepadua.forumhub.forumhub.repository.CursoRepository;
import com.adepadua.forumhub.forumhub.repository.TopicoRepository;
import com.adepadua.forumhub.forumhub.repository.UsuarioRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public void editarTopico(Long id, CriarTopicoDTO dto) {
    }
}
