package br.com.fiap.cp5.mercado_express.controller;

import br.com.fiap.cp5.mercado_express.entity.Papel;
import br.com.fiap.cp5.mercado_express.entity.Usuario;
import br.com.fiap.cp5.mercado_express.repository.PapelRepository;
import br.com.fiap.cp5.mercado_express.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PapelRepository papelRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // O Bean que criamos no SecurityConfig

    // --- MOSTRAR PÁGINA DE LOGIN ---
    @GetMapping("/login")
    public String mostrarPaginaLogin() {
        return "auth/login"; // (arquivo: templates/auth/login.html)
    }

    // --- MOSTRAR PÁGINA DE REGISTRO ---
    @GetMapping("/registrar")
    public String mostrarPaginaRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "auth/registrar"; // (arquivo: templates/auth/registrar.html)
    }

    // --- MOSTRAR A LANDING PAGE (index.html) ---
    @GetMapping("/")
    public String mostrarIndex() {
        return "index"; // (arquivo: templates/index.html)
    }


    // --- PROCESSAR O REGISTRO ---
    @PostMapping("/registrar")
    public String registrarUsuario(@ModelAttribute("usuario") Usuario usuario) {

        // 1. Criptografar a senha antes de salvar
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

        // 2. Definir o papel padrão (ROLE_USER) para o novo usuário
        // (O papel "ROLE_USER" deve existir no banco)
        Papel papelUser = papelRepository.findByNome("ROLE_USER");
        if (papelUser == null) {
            // Se não existir, cria e salva
            papelUser = new Papel();
            papelUser.setNome("ROLE_USER");
            papelRepository.save(papelUser);
        }

        Set<Papel> papeis = new HashSet<>();
        papeis.add(papelUser);
        usuario.setPapeis(papeis);

        // 3. Salvar o novo usuário no banco
        usuarioRepository.save(usuario);

        // 4. Redirecionar para a página de login com uma msg de sucesso
        return "redirect:/login?registroSucesso";
    }
}