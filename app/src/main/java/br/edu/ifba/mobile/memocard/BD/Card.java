package br.edu.ifba.mobile.memocard.BD;

/**
 * Created by André Sobreira on 01/07/2016.
 */
public class Card {

    private long codigo = -1;
    private String frente;
    private String verso;
    private int numRevisoes = 0;

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
    public int getNumRevisoes() {
        return numRevisoes;
    }
    public void setNumRevisoes(int numRevisoes){
        this.numRevisoes=numRevisoes;
    }

    @Override
    public String toString() {
        return frente+" | revisões: "+numRevisoes;
    }
}
