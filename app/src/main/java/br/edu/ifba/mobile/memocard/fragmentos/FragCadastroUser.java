package br.edu.ifba.mobile.memocard.fragmentos;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import br.edu.ifba.mobile.memocard.R;
import br.edu.ifba.mobile.memocard.BD.FachadaBD;
import br.edu.ifba.mobile.memocard.BD.User;
import br.edu.ifba.mobile.memocard.tarefas.GravacaoUser;

/**
 * Created by André Sobreira on 21/06/2016.
 */
public class FragCadastroUser  extends Fragment {

    private static FragCadastroUser instancia = null;

    public static FragCadastroUser getInstancia(){
        if (instancia==null){
            instancia=new FragCadastroUser();
        }
        return instancia;
    }

    private View tela = null;
    private EditText aluno = null;
    private EditText professor = null;
    private EditText escola = null;
    private EditText serie = null;
    private EditText materia = null;
    private EditText turno = null;
    private Button botaoSalvar = null;
    private User user = null;

    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup vgrupo, Bundle bundle){
        tela = inflador.inflate(R.layout.frag_cadastro_user, vgrupo, false);
        preparar();
        return tela;
    }

    private void preparar(){
        aluno = (EditText)tela.findViewById(R.id.conteudoAluno);
        professor = (EditText)tela.findViewById(R.id.conteudoProf);
        escola = (EditText)tela.findViewById(R.id.conteudoEscola);
        serie = (EditText)tela.findViewById(R.id.conteudoSerie);
        materia = (EditText)tela.findViewById(R.id.conteudoMateria);
        turno = (EditText)tela.findViewById(R.id.conteudoTurno);
        botaoSalvar = (Button) tela.findViewById(R.id.botaoSalvar);
        botaoSalvar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                GravacaoUser gravacao = new GravacaoUser(getContexto(), getUser());
                gravacao.execute();
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.M)
    private Context getContexto(){
        return this.getContext();
    }

    private User getUser(){
        user.setAluno(aluno.getText().toString());
        user.setProfessor(professor.getText().toString());
        user.setEscola(escola.getText().toString());
        user.setSerie(serie.getText().toString());
        user.setMateria(materia.getText().toString());
        user.setTurno(turno.getText().toString());
        return user;
    }

    public void exibirUsuario(){
        user = FachadaBD.getInstancia().listarUsuarios();
        if(user.getCodigo() == -1){
            limparCampos();
        } else
            carregarCampos();
    }

    private void limparCampos(){
        aluno.setText("");
        professor.setText("");
        escola.setText("");
        serie.setText("");
        materia.setText("");
        turno.setText("");
    }

    private void carregarCampos(){
        aluno.setText(user.getAluno());
        professor.setText(user.getProfessor());
        escola.setText(user.getEscola());
        serie.setText(user.getSerie());
        materia.setText(user.getMateria());
        turno.setText(user.getTurno());
    }

}
