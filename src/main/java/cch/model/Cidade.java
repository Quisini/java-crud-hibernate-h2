package cch.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "cidade")
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome da cidade é obrigatório")
    private String nome;

    // UF com no máximo 2 caracteres, conforme especificação do trabalho
    @NotBlank(message = "A UF é obrigatória")
    @Size(min = 2, max = 2, message = "A UF deve ter exatamente 2 caracteres")
    @Column(length = 2)
    private String uf;

    public Cidade() {
    }

    public Cidade(String nome, String uf) {
        this.nome = nome;
        this.uf = uf;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    // Usado para exibir a cidade de forma legível dentro do JComboBox
    // na tela de Cliente (ex: "Curitiba - PR")
    @Override
    public String toString() {
        return nome + " - " + uf;
    }
}
