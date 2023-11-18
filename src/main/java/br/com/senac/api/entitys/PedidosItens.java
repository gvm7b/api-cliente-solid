package br.com.senac.api.entitys;

import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

@Entity(name = "pedidos_itens")
@SQLDelete(sql = "UPDATE pedidos_itens SET deleted_at = now() WHERE id=?")
@Where(clause = "deleted_at is null")
public class PedidosItens {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private int quantidade;
    @Column
    private double valorUnitario;
    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedidos pedidosId;
    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produtos produtoId;
    @Column
    private LocalDateTime deleted_at;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Pedidos getPedidosId() {
        return pedidosId;
    }

    public void setPedidosId(Pedidos pedidosId) {
        this.pedidosId = pedidosId;
    }

    public Produtos getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Produtos produtoId) {
        this.produtoId = produtoId;
    }

    public LocalDateTime getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(LocalDateTime deleted_at) {
        this.deleted_at = deleted_at;
    }
}
