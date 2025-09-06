package com.example.FilmesAPI.repository;

import com.example.FilmesAPI.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {

    // Buscar por título (case insensitive)
    List<Filme> findByTituloContainingIgnoreCase(String titulo);

    // Buscar por diretor
    List<Filme> findByDiretorContainingIgnoreCase(String diretor);

    // Buscar por gênero
    List<Filme> findByGeneroIgnoreCase(String genero);

    // Buscar por ano
    List<Filme> findByAno(Integer ano);

    // Buscar por nota maior ou igual
    List<Filme> findByNotaGreaterThanEqual(Integer nota);

    // Ordenar por nota decrescente
    @Query("SELECT f FROM Filme f ORDER BY f.nota DESC")
    List<Filme> findAllOrderByNotaDesc();
}