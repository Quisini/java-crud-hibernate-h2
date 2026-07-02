package cch.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cidade")
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    // UF com no máximo 2 caracteres, conforme especificação do trabalho
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
