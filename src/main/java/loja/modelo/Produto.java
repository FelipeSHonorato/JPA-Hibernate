package loja.modelo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

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
    private LocalDate dataCadastro = LocalDate.now();

    //@Enumerated(EnumType.STRING) Através dessa anotação o tipo da coluna que será criada na tabela será de acordo com o nome das categorias do ENUM e não na ordem que vem como padrão
    @ManyToOne //Relação de muitos para um -> Muitos produtos podem ter a mesma categoria, porem muitas categorias não podem ter mesmo produto.
    @JoinColumn(name = "categoria_id") //Informa o campo que será inserido na tabela Produtos que será a chave estrangeira
    private Categoria categoria;

    public Categoria getCategoria() {
        return categoria;
    }

    public Produto() {
    }

    public Produto(String nome, String descricao, BigDecimal preco, Categoria categoria) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
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
