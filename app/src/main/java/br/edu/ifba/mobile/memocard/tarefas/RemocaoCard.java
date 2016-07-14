package br.edu.ifba.mobile.memocard.tarefas;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import br.edu.ifba.mobile.memocard.BD.Card;
import br.edu.ifba.mobile.memocard.BD.FachadaBD;
import br.edu.ifba.mobile.memocard.fragmentos.FragListaCards;

/**
 * Created by André Sobreira on 21/06/2016.
 */
public class RemocaoCard extends AsyncTask<Void, Void, String> {

    private Context contexto = null;
    private Card card = null;

    public RemocaoCard(Context contexto, Card card){
        this.card = card;
        this.contexto = contexto;
    }

    @Override
    protected String doInBackground(Void... voids) {
        String mensagem;
        if(card.getCodigo() != -1){
            if(FachadaBD.getInstancia().remover(card) == 0){
                mensagem = "Oops! não conseguimos remover.";
            } else {
                mensagem = "Seu card foi apagado.";
            }
        }
        else {
            mensagem = "Dica: escolha um card primeiro!";
        }
        return mensagem;
    }
    @Override
    protected void onPostExecute(String mensagem){
        Toast.makeText(contexto, mensagem, Toast.LENGTH_LONG).show();
        FragListaCards.getInstancia().atualizar();
    }
}
