package br.com.murilodev.dto;

import java.io.Serializable;

public class DadosCaixaEntregadorDTO implements Serializable {

    private Long id;
    private String nome;
    private String usuario;
    private String senha;
    private boolean ativo;

    public Long getId() {
        return id;
    }

    public void setIdCaixa(Long id) {
        this.id = id;
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

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
