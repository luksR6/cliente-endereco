package br.com.senac.clientes.entitys;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Enderecos extends EntidadeMaster {

    @Column(nullable = false, unique = true)
    private int cep;

    @Column(nullable = false, unique = true)
    private String logradouro;

    @Column(nullable = false, unique = true)
    private String bairro;

    @Column(nullable = false, unique = true)
    private String numero;

    @Column(nullable = false, unique = true)
    private String cidade;

    @Column(nullable = false, unique = true)
    private String estado;

    @Column(nullable = false)
    private Long clienteId;

    public int getCep() {
        return cep;
    }

    public void setCep(int cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }
}
