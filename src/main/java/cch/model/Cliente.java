package cch.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 
 * @author Gustavo
 */
@Entity
@Table(name = "cliente")
public class Cliente {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
    
    @NotBlank(message = "O nome do cliente é obrigatório")
    private String nome;
    
    private String cpf;
    private String email;
    private String telefone;
    
    @ManyToOne(optional = false)
    @NotNull(message = "A cidade é obrigatória")
    private Cidade cidade;

    public Cliente() {
    }

    public Cliente(String nome, String cpf, String email, String telefone, Cidade cidade) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.cidade = cidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf){
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    } 

    public void setTelefone(String telefone){
        this.telefone = telefone;
    }
    
    public Cidade getCidade(){
        return cidade;
    }
    
    public void setCidade(Cidade cidade){
        this.cidade = cidade;
    }
}
