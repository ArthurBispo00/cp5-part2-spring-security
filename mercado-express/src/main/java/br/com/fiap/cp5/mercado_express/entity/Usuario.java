package br.com.fiap.cp5.mercado_express.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set; // Importante: java.util.Set

@Entity
@Table(name = "TDS_SEC_MVC_TB_USUARIO") // Tabela de usuários (sugestão do professor: TDS_Users_Mercado)
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
    private String senha; // A senha será armazenada criptografada

    @Column(name = "nm_usuario")
    private String nome; // Nome do usuário

    // Relacionamento Muitos-para-Muitos
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "TDS_SEC_MVC_TB_USUARIO_PAPEL", // Nome da tabela de junção
            joinColumns = @JoinColumn(name = "id_usuario"), // Chave estrangeira para Usuario
            inverseJoinColumns = @JoinColumn(name = "id_papel") // Chave estrangeira para Papel
    )
    private Set<Papel> papeis; // Um conjunto de papéis (não permite papéis duplicados)
}