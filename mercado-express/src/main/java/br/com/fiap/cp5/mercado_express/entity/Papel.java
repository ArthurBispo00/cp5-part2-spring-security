package br.com.fiap.cp5.mercado_express.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TDS_SEC_MVC_TB_PAPEL")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Papel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_papel")
    private Long id;

    @Column(name = "nm_papel", nullable = false, unique = true)
    private String nome;
}