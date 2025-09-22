package com.example.java_crud.Controllers;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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

    @GetMapping
    public String listarClientes(Model model) {
        model.addAttribute("clientes", clienteService.listarTodos());
        return "cliente/index"; // vai alimentar a grid
    }

    @GetMapping("/novo")
    public String novoCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cliente/form";
    }

    @PostMapping("/salvar")
    public ResponseEntity<?> salvarCliente(@ModelAttribute Cliente cliente) {
        try {
            clienteService.salvar(cliente);
            return ResponseEntity.ok("Cliente salvo com sucesso"); // 200
        } catch (DataIntegrityViolationException ex) {
            String mensagem = "CPF ou Email já existe no sistema!";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagem); // 400
        }
    }

    @GetMapping("/editar/form/{id}")
    public String openEditarCliente(@PathVariable Long id, Model model) {
        var cliente = clienteService.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente inválido: " + id));
        model.addAttribute("cliente", cliente);
        return "cliente/form"; // abre o mesmo form, mas preenchido
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarCliente(@PathVariable Long id, @ModelAttribute Cliente cliente, Model model) {
        cliente.setId(id);
        try {
            clienteService.salvar(cliente);
            return ResponseEntity.ok("Cliente salvo com sucesso");
        } catch (DataIntegrityViolationException ex) {
            String mensagem = "CPF ou Email já existe no sistema!";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagem); // 400
        }
    }

    @GetMapping("/excluir/{id}")
    public String excluirCliente(@PathVariable Long id) {
        clienteService.excluir(id);
        return "redirect:/clientes";
    }
}

