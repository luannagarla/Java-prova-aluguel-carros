package com.example.java_crud.Controllers;
import java.util.NoSuchElementException;

import com.example.java_crud.Models.Carro;
import com.example.java_crud.Models.Venda;
import com.example.java_crud.Services.CarroService;
import com.example.java_crud.Services.ClienteService;
import com.example.java_crud.Services.FuncionarioService;
import com.example.java_crud.Services.VendaService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/vendas")
public class VendaController {

    private final VendaService vendaService;
    private final ClienteService clienteService;
    private final FuncionarioService funcionarioService;
    private final CarroService carroService;

    public VendaController(VendaService vendaService, ClienteService clienteService,
                           FuncionarioService funcionarioService, CarroService carroService) {
        this.vendaService = vendaService;
        this.clienteService = clienteService;
        this.funcionarioService = funcionarioService;
        this.carroService = carroService;
    }

    @GetMapping
    public String listarVendas(Model model) {
        model.addAttribute("vendas", vendaService.listarTodas());
        return "venda/index";
    }

    @GetMapping("/nova")
    public String novaVenda(Model model) {
        model.addAttribute("venda", new Venda());
        model.addAttribute("clientes", clienteService.listarTodos());
        model.addAttribute("funcionarios", funcionarioService.listarTodosAtivos());
        model.addAttribute("carros", carroService.listarDisponiveis());
        return "venda/form";
    }
    @PostMapping("/salvar")
    public ResponseEntity<?> salvarVenda(@ModelAttribute Venda venda) {
        try {
            // Buscar entidades reais a partir dos IDs
            venda.setCliente(clienteService.buscarPorId(venda.getCliente().getId()).orElseThrow());
            venda.setFuncionario(funcionarioService.buscarPorId(venda.getFuncionario().getId()).orElseThrow());
            venda.setCarro(carroService.buscarPorId(venda.getCarro().getId()).orElseThrow());

            vendaService.salvar(venda);
            return ResponseEntity.ok("Venda registrada com sucesso");
        } catch (DataIntegrityViolationException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao salvar venda! Verifique os dados.");
        } catch (NoSuchElementException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cliente, funcionário ou carro não encontrado!");
        }
    }

    @GetMapping("/editar/form/{id}")
    public String openEditarVenda(@PathVariable Long id, Model model) {
        var venda = vendaService.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Venda inválida: " + id));

        model.addAttribute("venda", venda);
        model.addAttribute("clientes", clienteService.listarTodos());
        model.addAttribute("funcionarios", funcionarioService.listarTodos());
        model.addAttribute("carros", carroService.listarTodos());

        return "venda/form"; // mesmo form, mas preenchido
    }

    @PostMapping("/editar/{id}")
    public ResponseEntity<?> editarVenda(@PathVariable Long id, @ModelAttribute Venda venda) {
        venda.setId(id);
        try {
            venda.setCliente(clienteService.buscarPorId(venda.getCliente().getId()).orElseThrow());
            venda.setFuncionario(funcionarioService.buscarPorId(venda.getFuncionario().getId()).orElseThrow());
            venda.setCarro(carroService.buscarPorId(venda.getCarro().getId()).orElseThrow());

            Venda vendaOriginal = vendaService.buscarPorId(id)
                    .orElseThrow(() -> new RuntimeException("Venda não encontrada!"));

            if (!(vendaOriginal.getCarro().getId() == venda.getCarro().getId())
                    && !venda.getCarro().isDisponivel()) {

                String mensagem = "Erro: Carro já alugado!\n" +
                        "ID do carro original: " + vendaOriginal.getCarro().getId() + "\n" +
                        "ID do carro da venda atual: " + venda.getCarro().getId() + "\n";
                System.out.print(mensagem);
                throw new RuntimeException(mensagem);
            }

            vendaService.salvar(venda);
            return ResponseEntity.ok("Venda atualizada com sucesso");
        } catch (DataIntegrityViolationException ex) {
            String mensagem = "Erro ao atualizar venda! Verifique os dados.";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagem);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/excluir/{id}")
    public String excluirVenda(@PathVariable Long id) {
        Venda venda = vendaService.buscarPorId(id)
                .orElseThrow(() -> new NoSuchElementException("Venda não encontrada: " + id));

        Carro carro = venda.getCarro();
        carro.setDisponivel(true);
        carroService.salvar(carro);

        vendaService.excluir(id);
        return "redirect:/vendas";
    }

}
