package com.example.java_crud.Controllers;

import com.example.java_crud.Services.FuncionarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    // Página de login
    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    // Validação login
    @PostMapping("/login")
    public String login(@RequestParam String matricula,
                        @RequestParam String senha,
                        Model model) {
        var funcionario = funcionarioService.login(matricula, senha);
        if (funcionario.isPresent()) {
            return "redirect:/dashboard";
        } else {
            model.addAttribute("erro", "Matrícula ou senha inválidos!");
            return "login";
        }
    }

    // Dashboard após login
    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }
}
