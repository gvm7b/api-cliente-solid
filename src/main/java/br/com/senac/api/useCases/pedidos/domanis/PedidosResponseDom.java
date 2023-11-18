package br.com.senac.api.useCases.pedidos.domanis;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PedidosResponseDom {
    private Long id;
    private LocalDateTime dataCriacao;
    private LocalDate dataEntrega;
    private Double valorDesconto;
    private Long clienteId;
    private Long enderecoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Double getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(Double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getEnderecoId() {
        return enderecoId;
    }

    public void setEnderecoId(Long enderecoId) {
        this.enderecoId = enderecoId;
    }

    @Override
    public String toString() {
        return "PedidosResponseDom{" +
                "id=" + id +
                ", dataCriacao=" + dataCriacao +
                ", dataEntrega=" + dataEntrega +
                ", valorDesconto=" + valorDesconto +
                ", clienteId=" + clienteId +
                ", enderecoId=" + enderecoId +
                '}';
    }
}
