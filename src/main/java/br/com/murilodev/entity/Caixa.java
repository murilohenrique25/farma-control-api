package br.com.murilodev.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "caixa")
public class Caixa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_caixa")
    private Long idCaixa;

    private String nome;

    private String usuario;

    private String senha;

    private Boolean ativo;

    public Long getIdCaixa() {
        return idCaixa;
    }

    public void setIdCaixa(Long idCaixa) {
        this.idCaixa = idCaixa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Caixa caixa = (Caixa) o;
        return Objects.equals(idCaixa, caixa.idCaixa) && Objects.equals(nome, caixa.nome) && Objects.equals(usuario, caixa.usuario) && Objects.equals(senha, caixa.senha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCaixa, nome, usuario, senha);
    }
}
