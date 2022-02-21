package loja.modelo;


import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "itens_pedido")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name= "preco_unitario")
    private BigDecimal precoUnitario;

    private int quantidade;

    @ManyToOne (fetch = FetchType.LAZY)
    /*Utilizado carregamento LAZY nas relações com final ToOne para evitar perdas de performance quando for efetuado algum tipo simples de consulta ao Banco de Dados.
    Por padrão todo relacionamento ToOne vem com o fetch IGGER carregado, então é o JPA automaticamente efetuado um JOIN seja qual for a consulta carregando todos
    os relacionamentos automaticamente.*/

    @JoinColumn(name = "pedido")
    private Pedido pedido;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id")
    private Produto produto;

    public ItemPedido() {
    }

    public ItemPedido(int quantidade, Pedido pedido, Produto produto) {
        this.quantidade = quantidade;
        this.pedido = pedido;
        this.precoUnitario = produto.getPreco();
        this.produto = produto;
    }

    public Produto getProduto() {
        return produto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public BigDecimal getValor(){
        return precoUnitario.multiply(new BigDecimal(quantidade));
    }
}
