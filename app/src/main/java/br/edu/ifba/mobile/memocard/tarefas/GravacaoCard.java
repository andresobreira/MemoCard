package br.edu.ifba.mobile.memocard.tarefas;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import br.edu.ifba.mobile.memocard.BD.Card;
import br.edu.ifba.mobile.memocard.BD.FachadaBD;

/**
 * Created by André Sobreira on 21/06/2016.
 */
public class GravacaoCard extends AsyncTask<Void, Void, String> {

    private Context contexto = null;
    private Card card = null;

    public GravacaoCard (Context contexto, Card card){
        this.contexto = contexto;
        this.card = card;
    }

    @Override
    protected String doInBackground(Void... voids) {
        String mensagem;
        long codigo;
        if(card.getCodigo() == -1){
            codigo = FachadaBD.getInstancia().inserir(card);
        }
        else{
            codigo = FachadaBD.getInstancia().atualizar(card);
        }
        if(codigo > 0){
            mensagem = "Yeah! Card salvo!";
        }
        else{
            mensagem = "Oops! O card não foi salvo!";
        }
        return mensagem;
    }
    @Override
    protected void onPostExecute(String mensagem){
        Toast.makeText(contexto, mensagem, Toast.LENGTH_LONG).show();
    }
}
