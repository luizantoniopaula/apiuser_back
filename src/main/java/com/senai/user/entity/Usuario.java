
package com.senai.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="usuarios")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;
    
    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false)
    private String email;
    
    @Column(nullable = true)
    private String fone;
    
    @Column(nullable = true)
    private String cpf;
    
    @Column(nullable = true)
    private String data_nascimento;

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setData_nascimento(String data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getFone() {
        return fone;
    }

    public String getCpf() {
        return cpf;
    }

    public String getData_nascimento() {
        return data_nascimento;
    }
    
    
    
    
}
