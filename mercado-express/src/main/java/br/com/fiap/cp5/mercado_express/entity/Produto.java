package br.com.fiap.cp5.mercado_express.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TDS_SEC_MVC_TB_MERCADO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto")
    private Long id;

    @Column(name = "nm_produto", length = 100, nullable = false)
    private String nome;

    @Column(name = "ds_produto", length = 255)
    private String descricao;

    @Column(name = "vl_preco", nullable = false)
    private double preco;

    @Column(name = "nr_estoque") // Número em estoque
    private int quantidadeEstoque;


}
