package com.example.FilmesAPI.service;

import com.example.FilmesAPI.model.Filme;
import com.example.FilmesAPI.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmeService {

    @Autowired
    private FilmeRepository filmeRepository;

    public List<Filme> listarTodos() {
        return filmeRepository.findAll();
    }

    public Filme buscarPorId(Long id) {
        return filmeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filme não encontrado com ID: " + id));
    }

    public Filme salvar(Filme filme) {
        return filmeRepository.save(filme);
    }

    public Filme atualizar(Long id, Filme filmeAtualizado) {
        Filme filmeExistente = buscarPorId(id);

        filmeExistente.setTitulo(filmeAtualizado.getTitulo());
        filmeExistente.setDiretor(filmeAtualizado.getDiretor());
        filmeExistente.setAno(filmeAtualizado.getAno());
        filmeExistente.setGenero(filmeAtualizado.getGenero());
        filmeExistente.setNota(filmeAtualizado.getNota());
        filmeExistente.setDescricao(filmeAtualizado.getDescricao());

        return filmeRepository.save(filmeExistente);
    }

    public Filme atualizarParcial(Long id, Filme filmeAtualizado) {
        Filme filmeExistente = buscarPorId(id);

        if (filmeAtualizado.getTitulo() != null) {
            filmeExistente.setTitulo(filmeAtualizado.getTitulo());
        }
        if (filmeAtualizado.getDiretor() != null) {
            filmeExistente.setDiretor(filmeAtualizado.getDiretor());
        }
        if (filmeAtualizado.getAno() != null) {
            filmeExistente.setAno(filmeAtualizado.getAno());
        }
        if (filmeAtualizado.getGenero() != null) {
            filmeExistente.setGenero(filmeAtualizado.getGenero());
        }
        if (filmeAtualizado.getNota() != null) {
            filmeExistente.setNota(filmeAtualizado.getNota());
        }
        if (filmeAtualizado.getDescricao() != null) {
            filmeExistente.setDescricao(filmeAtualizado.getDescricao());
        }

        return filmeRepository.save(filmeExistente);
    }

    public void deletar(Long id) {
        Filme filme = buscarPorId(id);
        filmeRepository.delete(filme);
    }

    public List<Filme> buscarPorTitulo(String titulo) {
        return filmeRepository.findByTituloContainingIgnoreCase(titulo);
    }

    public List<Filme> buscarPorDiretor(String diretor) {
        return filmeRepository.findByDiretorContainingIgnoreCase(diretor);
    }

    public List<Filme> buscarPorGenero(String genero) {
        return filmeRepository.findByGeneroIgnoreCase(genero);
    }

    public List<Filme> buscarPorNota(Integer nota) {
        return filmeRepository.findByNotaGreaterThanEqual(nota);
    }

    public List<Filme> listarPorNota() {
        return filmeRepository.findAllOrderByNotaDesc();
    }
}