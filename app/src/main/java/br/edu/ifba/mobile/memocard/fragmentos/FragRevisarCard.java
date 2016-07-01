package br.edu.ifba.mobile.memocard.fragmentos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import br.edu.ifba.mobile.memocard.BD.Card;
import br.edu.ifba.mobile.memocard.R;
import br.edu.ifba.mobile.memocard.tarefas.ContagemRevisoes;

/**
 * Created by André Sobreira on 30/06/2016.
 */
public class FragRevisarCard extends Fragment {

    private static FragRevisarCard instancia = null;

    public static FragRevisarCard getInstancia(){
        if (instancia==null){
            instancia=new FragRevisarCard();
        }
        return instancia;
    }

    private View tela = null;
    private EditText frente = null;
    private EditText verso = null;
    private Button botaoVirarCard = null;
    private Button botaoAnt = null;
    private Button botaoProx = null;
    private Card card = null;

    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup vgrupo, Bundle bundle){
        tela = inflador.inflate(R.layout.frag_revisar_card, vgrupo, false);
        preparar();
        return tela;
    }

    private void preparar(){
        frente = (EditText)tela.findViewById(R.id.contFrente);
        verso = (EditText)tela.findViewById(R.id.contVerso);
        botaoAnt = (Button) tela.findViewById(R.id.botaoAnt);
        botaoAnt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                exibirCardAnterior();
            }
        });
        botaoProx = (Button) tela.findViewById(R.id.botaoProx);
        botaoProx.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                exibirCardProximo();
            }
        });
        botaoVirarCard = (Button) tela.findViewById(R.id.botaoVirarCard);
        botaoVirarCard.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                verso.setText(card.getVerso());
                card.setNumRevisoes(card.getNumRevisoes()+1);
                ContagemRevisoes contagem = new ContagemRevisoes(getContext(), card);
                contagem.execute();
            }
        });
    }

    public void exibirCardSelecionado(){
        card = FragListaCards.getInstancia().getCardSelecionado();
        if(card.getCodigo() == -1 || card == null){
            limparCampos();
        } else {
            frente.setText(card.getFrente());
            verso.setText("");
        }
    }

    public void exibirCardAnterior(){
        card = FragListaCards.getInstancia().getCardAnterior();
        if(card.getCodigo() == -1){
            limparCampos();
        } else {
            frente.setText(card.getFrente());
            verso.setText("");
        }
    }

    public void exibirCardProximo(){
        card = FragListaCards.getInstancia().getCardProximo();
        if(card.getCodigo() == -1){
            limparCampos();
        } else {
            frente.setText(card.getFrente());
            verso.setText("");
        }
    }

    private void limparCampos(){
        frente.setText("");
        verso.setText("");
    }

}
