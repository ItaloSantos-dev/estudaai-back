package br.com.italo.estuda_ai;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class teste {
    @GetMapping
    public String teste(){
        return "Deu bom";
    }
}
