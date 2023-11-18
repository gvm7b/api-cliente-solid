package br.com.senac.api.useCases.clientes.domanis;

import java.time.LocalDate;
import java.util.List;

public class ClientesResponseDom {
    private Long id;
    private String nome;
    private String sobrenome;
    private LocalDate dataNascimento;
    private String telefone;
    private String email;

    private List<ClientesEnderecosResponseDom> enderecos;

    private List<ClientesPedidosResponseDom> pedidos;

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

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<ClientesEnderecosResponseDom> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<ClientesEnderecosResponseDom> enderecos) {
        this.enderecos = enderecos;
    }

    public List<ClientesPedidosResponseDom> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<ClientesPedidosResponseDom> pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public String toString() {
        return "ClientesResponseDom{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", enderecos=" + enderecos +
                '}';
    }
}
