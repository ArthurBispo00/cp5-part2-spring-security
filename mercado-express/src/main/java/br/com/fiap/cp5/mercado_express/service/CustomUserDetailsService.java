package br.com.fiap.cp5.mercado_express.service;

import br.com.fiap.cp5.mercado_express.entity.Usuario;
import br.com.fiap.cp5.mercado_express.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service // Marca esta classe como um "Serviço" do Spring
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Este é o método principal que o Spring Security vai chamar
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // 1. Busca o usuário no banco de dados pelo email
        Usuario usuario = usuarioRepository.findByEmail(email);

        // 2. Verifica se o usuário foi encontrado
        if (usuario == null) {
            throw new UsernameNotFoundException("Email não encontrado: " + email);
        }

        // 3. Converte os "Papéis" (nossa entidade Papel) para "Authorities" (o que o Spring entende)
        Collection<? extends GrantedAuthority> authorities = usuario.getPapeis().stream()
                .map(papel -> new SimpleGrantedAuthority(papel.getNome()))
                .collect(Collectors.toList());

        // 4. Retorna um objeto "User" do Spring Security
        // Ele contém o email (username), a senha (CRIPTOGRAFADA) e as permissões
        return new User(usuario.getEmail(), usuario.getSenha(), authorities);
    }
}