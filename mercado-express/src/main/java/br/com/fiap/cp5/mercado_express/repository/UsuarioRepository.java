package br.com.fiap.cp5.mercado_express.repository;

import br.com.fiap.cp5.mercado_express.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Método para buscar um usuário pelo email (nosso "username")
    Usuario findByEmail(String email);
}