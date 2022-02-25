package loja.modelo;

import javax.persistence.Entity;

@Entity
public class ProdutoLivro extends Produto {

    private String autor;
    private Integer numeroDePaginas;

    public ProdutoLivro() {
    }

    public ProdutoLivro(String autor, Integer numeroDePaginas) {
        this.autor = autor;
        this.numeroDePaginas = numeroDePaginas;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Integer getNumeroDePaginas() {
        return numeroDePaginas;
    }

    public void setNumeroDePaginas(Integer numeroDePaginas) {
        this.numeroDePaginas = numeroDePaginas;
    }
}
