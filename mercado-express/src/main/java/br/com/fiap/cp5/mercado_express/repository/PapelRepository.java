package br.com.fiap.cp5.mercado_express.repository;

import br.com.fiap.cp5.mercado_express.entity.Papel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PapelRepository extends JpaRepository<Papel, Long> {
    // Método para buscar um papel pelo nome exato
    Papel findByNome(String nome);
}