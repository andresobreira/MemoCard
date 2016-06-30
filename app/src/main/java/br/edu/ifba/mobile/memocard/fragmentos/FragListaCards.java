package br.edu.ifba.mobile.memocard.fragmentos;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import br.edu.ifba.mobile.memocard.BD.Card;
import br.edu.ifba.mobile.memocard.R;
import br.edu.ifba.mobile.memocard.tarefas.ListagemCard;
import br.edu.ifba.mobile.memocard.tarefas.RemocaoCard;

/**
 * Created by André Sobreira on 21/06/2016.
 */
public class FragListaCards extends Fragment {

    private static FragListaCards instancia = null;

    public static FragListaCards getInstancia(){
        if (instancia == null){
            instancia = new FragListaCards();
        }
        return instancia;
    }

    private View tela = null;
    private ListView lista = null;
    private int posicao = 0;

    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup vgrupo, Bundle bundle){
        tela = inflador.inflate(R.layout.frag_lista_cards, vgrupo, false);
        preparar();
        return tela;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflador){
        super.onCreateOptionsMenu(menu, inflador);
        inflador.inflate(R.menu.menu_controle_cards, menu);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        long id = item.getItemId();
        if (id != AdapterView.INVALID_ROW_ID){
            if (id == R.id.cadastro_remover){
                RemocaoCard remocao = new RemocaoCard(this.getContext(), this.getCardSelecionado());
                remocao.execute();
                atualizar();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void preparar(){
        lista = (ListView) tela.findViewById(R.id.listaCards);
        this.setHasOptionsMenu(true);
        lista.setClickable(true);
        lista.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void atualizar(){
        ListagemCard listagem = new ListagemCard(this.getContext(), lista);
        listagem.execute();
    }

    public int getPosicao(){
        return posicao;
    }

    public Card getCardSelecionado(){
        Card card = new Card();
        posicao = lista.getCheckedItemPosition();
        if (posicao != ListView.INVALID_POSITION){
            card = (Card) lista.getItemAtPosition(posicao);
        }
        return card;
    }

    public Card getCardAnterior(){
        Card card = (Card) lista.getItemAtPosition(posicao-1);
        if (posicao >=0 && card!=null){
            posicao--;
            return card;
        }else {
            card = new Card();
            return card;
        }
    }

    public Card getCardProximo(){
        Card card = (Card) lista.getItemAtPosition(posicao+1);
        if (card!=null){
            posicao++;
            return card;
        }else {
            card = new Card();
            return card;
        }
    }

}