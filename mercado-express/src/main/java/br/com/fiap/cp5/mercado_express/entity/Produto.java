package br.com.fiap.cp5.mercado_express.entity;

import jakarta.persistence.*; // Importante para as anotações JPA
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // Marca esta classe como uma entidade (tabela) do banco de dados
@Table(name = "TDS_SEC_MVC_TB_MERCADO") // Define o nome da tabela (sugestão do professor)
@Data // Lombok: Gera getters, setters, toString, equals, hashCode
@NoArgsConstructor // Lombok: Gera um construtor vazio (obrigatório para JPA)
@AllArgsConstructor // Lombok: Gera um construtor com todos os campos
public class Produto {

    @Id // Marca este campo como a Chave Primária (PK)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Define como auto-incremento
    @Column(name = "id_produto") // Nome da coluna da PK
    private Long id;

    @Column(name = "nm_produto", length = 100, nullable = false) // Nome do produto, 100 chars, não pode ser nulo
    private String nome;

    @Column(name = "ds_produto", length = 255) // Descrição do produto, 255 chars
    private String descricao;

    @Column(name = "vl_preco", nullable = false) // Valor do preço, não pode ser nulo
    private double preco;

    @Column(name = "nr_estoque") // Número em estoque
    private int quantidadeEstoque;

    // Graças às anotações do Lombok (@Data, @NoArgsConstructor, @AllArgsConstructor),
    // não precisamos escrever nenhum método getter, setter ou construtor!
}
