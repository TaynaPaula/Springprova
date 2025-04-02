package com.example.CatracaAcademia.UsuarioController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CatracaAcademia.model.Cliente;
import com.example.CatracaAcademia.model.Funcionario;
import com.example.CatracaAcademia.model.Usuario;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/academia")
public class UsuarioController {

    private List<Usuario> usuarios = new ArrayList<>();

    @PostMapping("/adicionar")
    public ResponseEntity<String> adicionarUsuario(@RequestBody @Valid Usuario usuario) {
        if (usuario instanceof Cliente) {
            usuarios.add((Cliente) usuario);
        } else if (usuario instanceof Funcionario) {
            usuarios.add((Funcionario) usuario);
        } else {
            return ResponseEntity.badRequest().body("Tipo de usuário inválido");
        }
        return ResponseEntity.ok("Usuário adicionado com sucesso!");
    }

    @GetMapping("/clientes")
    public ResponseEntity<List<Cliente>> listarClientes() {
        List<Cliente> clientes = usuarios.stream()
            .filter(Cliente.class::isInstance)
            .map(Cliente.class::cast)
            .collect(Collectors.toList());
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/funcionarios")
    public ResponseEntity<List<Funcionario>> listarFuncionarios() {
        List<Funcionario> funcionarios = usuarios.stream()
            .filter(Funcionario.class::isInstance)
            .map(Funcionario.class::cast)
            .collect(Collectors.toList());
        return ResponseEntity.ok(funcionarios);
    }

    @PutMapping("/atualizar")
    public ResponseEntity<String> atualizarUsuario(@RequestBody @Valid Usuario usuario) {
        for (Usuario u : usuarios) {
            if (u.getCpf().equals(usuario.getCpf()) && usuario.getIdade() > 18) {
                u.setNome(usuario.getNome());
                return ResponseEntity.ok("Nome atualizado com sucesso!");
            }
        }
        return ResponseEntity.badRequest().body("Não foi possível atualizar.");
    }
}