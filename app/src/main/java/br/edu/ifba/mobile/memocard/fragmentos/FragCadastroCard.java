package br.edu.ifba.mobile.memocard.fragmentos;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import br.edu.ifba.mobile.memocard.BD.Card;
import br.edu.ifba.mobile.memocard.R;
import br.edu.ifba.mobile.memocard.tarefas.GravacaoCard;

/**
 * Created by André Sobreira on 20/06/2016.
 */
@SuppressWarnings("FieldCanBeLocal")
public class FragCadastroCard extends Fragment {

    private static FragCadastroCard instancia = null;

    public static FragCadastroCard getInstancia(){
        if (instancia==null){
            instancia=new FragCadastroCard();
        }
        return instancia;
    }

    private View tela = null;
    private EditText frente = null;
    private EditText verso = null;
    @SuppressWarnings("FieldCanBeLocal")
    private Button botaoSalvar = null;
    private Card card = null;

    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup vgrupo, Bundle bundle){
        tela = inflador.inflate(R.layout.frag_cadastro_card, vgrupo, false);
        preparar();
        return tela;
    }

    private void preparar(){
        frente = (EditText)tela.findViewById(R.id.conteudoFrente);
        verso = (EditText)tela.findViewById(R.id.conteudoVerso);
        botaoSalvar = (Button) tela.findViewById(R.id.botaoSalvar);
        botaoSalvar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                GravacaoCard gravacao = new GravacaoCard(getContexto(), getCard());
                gravacao.execute();
                limparCampos();
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.M)
    private Context getContexto(){
        return this.getContext();
    }

    private Card getCard(){
        card.setFrente(frente.getText().toString());
        card.setVerso(verso.getText().toString());
        return card;
    }

    public void exibirCardSelecionado(){
        card = FragListaCards.getInstancia().getCardSelecionado();
        if(card.getCodigo() == -1){
            limparCampos();
        } else
            carregarCampos();
    }

    private void limparCampos(){
        frente.setText("");
        verso.setText("");
    }

    private void carregarCampos(){
        frente.setText(card.getFrente());
        verso.setText(card.getVerso());
    }

}

