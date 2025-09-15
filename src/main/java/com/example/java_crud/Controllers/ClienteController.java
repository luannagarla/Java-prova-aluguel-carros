package com.example.java_crud.Controllers;

import com.example.java_crud.Models.Cliente;
import com.example.java_crud.Services.ClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // Listagem
    @GetMapping
    public String listarClientes(Model model) {
        model.addAttribute("clientes", clienteService.listarTodos());
        return "clientes/lista"; // Thymeleaf
    }

    // Formulário novo cliente
    @GetMapping("/novo")
    public String novoCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "clientes/form";
    }

    // Salvar cliente
    @PostMapping("/salvar")
    public String salvarCliente(@ModelAttribute Cliente cliente) {
        clienteService.salvar(cliente);
        return "redirect:/clientes";
    }

    // Editar cliente
    @GetMapping("/editar/{id}")
    public String editarCliente(@PathVariable Long id, Model model) {
        var cliente = clienteService.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente inválido: " + id));
        model.addAttribute("cliente", cliente);
        return "clientes/form";
    }

    // Excluir cliente
    @GetMapping("/excluir/{id}")
    public String excluirCliente(@PathVariable Long id) {
        clienteService.excluir(id);
        return "redirect:/clientes";
    }
}
