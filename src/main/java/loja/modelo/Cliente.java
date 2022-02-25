package loja.modelo;

import javax.persistence.*;
import javax.persistence.Embedded;

@Entity
@Table(name="clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    //Informa para JPA que essa classe esta sendo anexada a esta entidade
    @Embedded
    private DadosPessoais dadosPessoais;

    public Cliente() {
    }

    //Por as variáveis se encontrarem em uma classe Embeddeble no construtor deve ser criado um objeto indicando a entrada das variáveis
    public Cliente(String nome, String cpf) {
        this.dadosPessoais = new DadosPessoais (nome, cpf);
    }

    public String getNome() {
        return this.dadosPessoais.getNome();
    }

    public String getCpf() {
        return this.dadosPessoais.getCpf();
    }

    public DadosPessoais getDadosPessoais() {
        return dadosPessoais;
    }

    public Long getId() {
        return id;
    }

}
