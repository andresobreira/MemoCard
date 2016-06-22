package br.edu.ifba.mobile.memocard.tarefas;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import br.edu.ifba.mobile.memocard.BD.FachadaBD;
import br.edu.ifba.mobile.memocard.BD.User;

/**
 * Created by André Sobreira on 21/06/2016.
 */
public class GravacaoUser extends AsyncTask<Void, Void, String> {

    private Context contexto = null;
    private User user = null;

    public GravacaoUser(Context contexto, User user) {
        this.contexto = contexto;
        this.user = user;
    }

    @Override
    protected String doInBackground(Void... voids) {
        String mensagem;
        long codigo;
        if(user.getCodigo() == -1){
            codigo = FachadaBD.getInstancia().inserir(user);
        }
        else{
            codigo = FachadaBD.getInstancia().atualizar(user);
        }
        if(codigo > 0){
            mensagem = "Yeah! Já guardamos seus dados!";
        }
        else{
            mensagem = "Oops! Não conseguimos salvar!";
        }
        return mensagem;
    }
    @Override
    protected void onPostExecute(String mensagem){
        Toast.makeText(contexto, mensagem, Toast.LENGTH_LONG).show();
    }
}
