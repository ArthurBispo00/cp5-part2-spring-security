package br.com.fiap.cp5.mercado_express.controller;

import br.com.fiap.cp5.mercado_express.entity.Produto;
import br.com.fiap.cp5.mercado_express.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/produtos") // Todos os métodos nesta classe começarão com /produtos
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;


    @GetMapping
    public String listarProdutos(Model model) {

        model.addAttribute("produtos", produtoRepository.findAll());

        return "produtos/lista";
    }

    // --- MOSTRAR FORMULÁRIO DE NOVO PRODUTO (CREATE) ---
    @GetMapping("/novo")
    public String mostrarFormularioNovo(Model model) {

        model.addAttribute("produto", new Produto());

        return "produtos/form";
    }


    @PostMapping("/salvar")
    public String salvarProduto(@ModelAttribute Produto produto) {

        produtoRepository.save(produto);

        return "redirect:/produtos";
    }

    // --- MOSTRAR FORMULÁRIO DE EDIÇÃO (UPDATE) ---
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {

        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isPresent()) {
            model.addAttribute("produto", produto.get());
            return "produtos/form";
        }
        return "redirect:/produtos";
    }

    // --- DELETAR PRODUTO (DELETE) ---
    @GetMapping("/deletar/{id}")
    public String deletarProduto(@PathVariable Long id) {
        produtoRepository.deleteById(id);
        return "redirect:/produtos";
    }
}