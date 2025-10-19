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

    @Autowired // Injeção de Dependência: O Spring nos dá o repository pronto
    private ProdutoRepository produtoRepository;

    // --- LISTAR TODOS (READ) ---
    @GetMapping
    public String listarProdutos(Model model) {
        // Busca todos os produtos no banco
        model.addAttribute("produtos", produtoRepository.findAll());
        // Retorna o nome do arquivo HTML que deve ser exibido
        return "produtos/lista"; // (arquivo: templates/produtos/lista.html)
    }

    // --- MOSTRAR FORMULÁRIO DE NOVO PRODUTO (CREATE) ---
    @GetMapping("/novo")
    public String mostrarFormularioNovo(Model model) {
        // Cria um objeto Produto vazio para o formulário preencher
        model.addAttribute("produto", new Produto());
        // Retorna o nome do arquivo HTML do formulário
        return "produtos/form"; // (arquivo: templates/produtos/form.html)
    }

    // --- SALVAR NOVO PRODUTO (CREATE) ---
    @PostMapping("/salvar")
    public String salvarProduto(@ModelAttribute Produto produto) {
        // O @ModelAttribute pega os dados do formulário e os coloca no objeto 'produto'
        produtoRepository.save(produto);
        // Redireciona o usuário de volta para a lista de produtos
        return "redirect:/produtos";
    }

    // --- MOSTRAR FORMULÁRIO DE EDIÇÃO (UPDATE) ---
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        // Busca o produto no banco pelo ID
        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isPresent()) {
            model.addAttribute("produto", produto.get());
            return "produtos/form"; // Reutiliza o mesmo formulário
        }
        return "redirect:/produtos"; // Se não achar, volta para a lista
    }

    // --- DELETAR PRODUTO (DELETE) ---
    @GetMapping("/deletar/{id}")
    public String deletarProduto(@PathVariable Long id) {
        produtoRepository.deleteById(id);
        return "redirect:/produtos";
    }
}