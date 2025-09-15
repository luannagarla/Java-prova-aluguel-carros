package com.example.java_crud.Controllers;

import com.example.java_crud.Models.Venda;
import com.example.java_crud.Services.CarroService;
import com.example.java_crud.Services.ClienteService;
import com.example.java_crud.Services.FuncionarioService;
import com.example.java_crud.Services.VendaService;
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
        return "vendas/lista";
    }

    @GetMapping("/nova")
    public String novaVenda(Model model) {
        model.addAttribute("venda", new Venda());
        model.addAttribute("clientes", clienteService.listarTodos());
        model.addAttribute("funcionarios", funcionarioService.listarTodos());
        model.addAttribute("carros", carroService.listarDisponiveis());
        return "vendas/form";
    }

    @PostMapping("/salvar")
    public String salvarVenda(@ModelAttribute Venda venda) {
        vendaService.salvar(venda);
        return "redirect:/vendas";
    }

    @GetMapping("/excluir/{id}")
    public String excluirVenda(@PathVariable Long id) {
        vendaService.excluir(id);
        return "redirect:/vendas";
    }
}
