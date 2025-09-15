package com.example.java_crud.Controllers;

import com.example.java_crud.Models.Carro;
import com.example.java_crud.Services.CarroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/carros")
public class CarroController {

    private final CarroService carroService;

    public CarroController(CarroService carroService) {
        this.carroService = carroService;
    }

    @GetMapping
    public String listarCarros(Model model) {
        model.addAttribute("carros", carroService.listarTodos());
        return "carros/lista";
    }

    @GetMapping("/novo")
    public String novoCarro(Model model) {
        model.addAttribute("carro", new Carro());
        return "carros/form";
    }

    @PostMapping("/salvar")
    public String salvarCarro(@ModelAttribute Carro carro) {
        carroService.salvar(carro);
        return "redirect:/carros";
    }

    @GetMapping("/editar/{id}")
    public String editarCarro(@PathVariable Long id, Model model) {
        var carro = carroService.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Carro inválido: " + id));
        model.addAttribute("carro", carro);
        return "carros/form";
    }

    @GetMapping("/excluir/{id}")
    public String excluirCarro(@PathVariable Long id) {
        carroService.excluir(id);
        return "redirect:/carros";
    }
}
