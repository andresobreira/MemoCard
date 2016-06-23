package br.edu.ifba.mobile.memocard.tarefas;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.edu.ifba.mobile.memocard.BD.Card;
import br.edu.ifba.mobile.memocard.BD.FachadaBD;

/**
 * Created by André Sobreira on 21/06/2016.
 */
public class ListagemCard extends AsyncTask<Void, Void, List<Card>> {

    private Context contexto = null;
    private ListView listaCards = null;

    public ListagemCard(Context contexto, ListView listaCards){
        this.contexto = contexto;
        this.listaCards = listaCards;
    }

    @Override
    protected List<Card> doInBackground(Void... voids) {
        List<Card> cards = FachadaBD.getInstancia().listarCards();
        return cards;
    }

    @Override
    protected void onPostExecute(List<Card> cards){
        if(cards.isEmpty()){
            Toast.makeText(contexto, "Nenhum card anotado... ainda!", Toast.LENGTH_SHORT).show();
        }
        else{
            //ArrayAdapter<Card> adaptador = new ArrayAdapter<Card>(contexto, android.R.layout.simple_list_item_single_choice, cards);
            listaCards.setAdapter(new ArrayAdapter<>(contexto, android.R.layout.simple_list_item_single_choice, cards));
        }
    }
}
