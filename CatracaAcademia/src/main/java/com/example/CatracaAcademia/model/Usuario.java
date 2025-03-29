package com.example.CatracaAcademia.model;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Usuario {
    @NotBlank(message = "O nome não pode estar em branco")
    private String nome;
 
   
    @NotNull(message = "A idade não pode ser nula")
    private Integer idade;
 
    @NotBlank(message = "O e-mail não pode estar em branco")
    @Email
    private String email;
 
    @CPF
    @NotBlank(message = "O CPF não pode estar em branco")
    private String cpf;
   
 
    public Usuario(String nome, Integer idade, String email, String cpf) {
        this.nome = nome;
        this.idade = idade;
        this.email = email;
        this.cpf = cpf;
    }
 
 
    public String getNome() {
        return nome;
    }
 
    public void setNome(String nome) {
        this.nome = nome;
    }
 
 
    public Integer getIdade() {
        return idade;
    }
 
    public void setIdade(Integer idade) {
        this.idade = idade;
    }
 
   
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getCpf() {
        return cpf;
    }
 
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
