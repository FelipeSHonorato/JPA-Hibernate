package loja.modelo;

import javax.persistence.Entity;

@Entity
public class ProdutoComputadores extends Produto{

    private String marca;
    private String processador;
    private String memoriaRam;

    public ProdutoComputadores() {
    }

    public ProdutoComputadores(String marca, String processador, String memoriaRam) {
        this.marca = marca;
        this.processador = processador;
        this.memoriaRam = memoriaRam;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getProcessador() {
        return processador;
    }

    public void setProcessador(String processador) {
        this.processador = processador;
    }

    public String getMemoriaRam() {
        return memoriaRam;
    }

    public void setMemoriaRam(String memoriaRam) {
        this.memoriaRam = memoriaRam;
    }
}
