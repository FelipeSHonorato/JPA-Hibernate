package loja.modelo;

public enum Cor {
    AZUL ("azul"),
    PRETO ( "preto"),
    VERMELHO ("vermelho");

    private String valor;

    Cor(String valor){
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
