package br.com.fiap.cp5.mercado_express.config;

import br.com.fiap.cp5.mercado_express.entity.Papel;
import br.com.fiap.cp5.mercado_express.entity.Usuario;
import br.com.fiap.cp5.mercado_express.repository.PapelRepository;
import br.com.fiap.cp5.mercado_express.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PapelRepository papelRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        // --- 1. Criar os PAPÉIS (Roles) se não existirem ---
        Papel roleUser = papelRepository.findByNome("ROLE_USER");
        if (roleUser == null) {
            roleUser = new Papel();
            roleUser.setNome("ROLE_USER");
            papelRepository.save(roleUser);
        }

        Papel roleAdmin = papelRepository.findByNome("ROLE_ADMIN");
        if (roleAdmin == null) {
            roleAdmin = new Papel();
            roleAdmin.setNome("ROLE_ADMIN");
            papelRepository.save(roleAdmin);
        }

        // --- 2. Criar o usuário ADMIN se não existir ---
        if (usuarioRepository.findByEmail("admin@email.com") == null) {

            Usuario admin = new Usuario();
            admin.setNome("Administrador");
            admin.setEmail("admin@email.com");
            // Criptografa a senha "admin123"
            admin.setSenha(passwordEncoder.encode("admin123"));

            // Define os papéis para o admin
            Set<Papel> papeis = new HashSet<>();
            papeis.add(roleUser);
            papeis.add(roleAdmin);
            admin.setPapeis(papeis);

            // Salva o admin no banco
            usuarioRepository.save(admin);
        }

        // --- 3. Criar um usuário USER comum se não existir ---
        if (usuarioRepository.findByEmail("user@email.com") == null) {

            Usuario user = new Usuario();
            user.setNome("Usuario Comum");
            user.setEmail("user@email.com");
            // Criptografa a senha "user123"
            user.setSenha(passwordEncoder.encode("user123"));

            // Define o papel para o user
            Set<Papel> papeis = new HashSet<>();
            papeis.add(roleUser);
            user.setPapeis(papeis);

            // Salva o user no banco
            usuarioRepository.save(user);
        }
    }
}