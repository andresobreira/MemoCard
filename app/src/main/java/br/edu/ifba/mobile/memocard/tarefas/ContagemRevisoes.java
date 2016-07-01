package br.edu.ifba.mobile.memocard.tarefas;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import br.edu.ifba.mobile.memocard.BD.Card;
import br.edu.ifba.mobile.memocard.BD.FachadaBD;

/**
 * Created by André Sobreira on 01/07/2016.
 */
public class ContagemRevisoes extends AsyncTask<Void, Void, String> {

    private Context contexto = null;
    private Card card = null;

    public ContagemRevisoes (Context contexto, Card card){
        this.contexto = contexto;
        this.card = card;
    }

    @Override
    protected String doInBackground(Void... params) {
        long codigo = FachadaBD.getInstancia().contarRevisoes(card);

        if(codigo > 0){
            return "Yes! Keep going!";
        }
        else{
            return "Oops!";
        }
    }
    @Override
    protected void onPostExecute(String mensagem){
        Toast.makeText(contexto, mensagem, Toast.LENGTH_LONG).show();
    }
}
