package br.edu.ifba.mobile.memocard.BD;


/**
 * Created by André Sobreira on 20/06/2016.
 */
public class Card {

    private long codigo = -1;
    private String frente;
    private String verso;

    //getters and setters:

    public long getCodigo() {
        return codigo;
    }
    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }
    public String getFrente() {
        return frente;
    }
    public void setFrente(String frente) {
        this.frente = frente;
    }
    public String getVerso() {
        return verso;
    }
    public void setVerso(String verso) {
        this.verso = verso;
    }

    @Override
    public String toString() {
        return frente;
    }
}
