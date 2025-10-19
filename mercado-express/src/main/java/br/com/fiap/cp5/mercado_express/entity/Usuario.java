package br.com.fiap.cp5.mercado_express.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set; // Importante: java.util.Set

@Entity
@Table(name = "TDS_SEC_MVC_TB_USUARIO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    @Column(name = "ds_email", nullable = false, unique = true) // Email será nosso "username"
    private String email;

    @Column(name = "ds_senha", nullable = false)
    private String senha;

    @Column(name = "nm_usuario")
    private String nome;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "TDS_SEC_MVC_TB_USUARIO_PAPEL",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_papel")
    )
    private Set<Papel> papeis;
}