package br.com.fiap.cp5.mercado_express.repository;

import br.com.fiap.cp5.mercado_express.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}