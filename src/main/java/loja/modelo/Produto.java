package loja.modelo;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity //Informa que essa classe é uma tabela no banco de dados
@Table(name = "produtos") //Anotação utilizada para informar ao java que apesar do nome da classe ser Produto o nome da tabela será produtos
public class Produto {

    @Id //Informa que esse atributo é a primary key da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Informar que esse atributo será gerado automaticamente pelo banco de dados através do padrão IDENTITY
    private Long id;

    private String nome;

    // @Column(name = "desc") - Sobre o atributo (nome coluna) caso o nome da DB tivesse que ser diferente do nome do atributo.
    private String descricao;

    private BigDecimal preco;

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}
