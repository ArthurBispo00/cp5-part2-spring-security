package br.com.fiap.cp5.mercado_express.repository;

import br.com.fiap.cp5.mercado_express.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Opcional para JpaRepository, mas é uma boa prática
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    // É SÓ ISSO!

    // O JpaRepository<Produto, Long> nos dá automaticamente métodos como:
    // - save(Produto) -> salva ou atualiza um produto
    // - findById(Long) -> busca um produto pelo ID
    // - findAll() -> busca todos os produtos
    // - deleteById(Long) -> deleta um produto pelo ID
    //
    // <Produto, Long> significa "Este repositório gerencia a entidade 'Produto',
    // e o tipo da chave primária (PK) do 'Produto' é 'Long'".
}