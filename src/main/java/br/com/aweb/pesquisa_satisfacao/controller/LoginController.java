package br.com.aweb.pesquisa_satisfacao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller 
public class LoginController {
    @GetMapping("/login")
    public String exibirLogin() {
        return "login";
    }
    
}
