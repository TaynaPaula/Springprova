package com.example.CatracaAcademia.UsuarioController;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.CatracaAcademia.model.Cliente;
import com.example.CatracaAcademia.model.Funcionario;
import com.example.CatracaAcademia.model.Usuario;

import jakarta.validation.Valid;
 


@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

    private List<Usuario> usuarios = new ArrayList<>();
    
    @GetMapping("/findbyname")
    public ResponseEntity<List<Usuario>> buscarUsuariosPorNome(@RequestParam(value = "name") String name) {
        List<Usuario> usuariosFiltrados = usuarios.stream()
                .filter(usuario -> usuario.getNome().equalsIgnoreCase(name))
                .collect(Collectors.toList());
        if (usuariosFiltrados.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuariosFiltrados);
    }


    @PostMapping("/usuarios")
    public ResponseEntity<List<Usuario>>inserirPessoa(@RequestBody @Valid Usuario usuario) {
        usuarios.add(usuario);
        System.out.println("Usuario inserido com sucesso: " + usuario);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return ResponseEntity.ok(usuarios);
    }


    @GetMapping("/cliente")
    public ResponseEntity<List<Cliente>> listarAlunos() {
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

}