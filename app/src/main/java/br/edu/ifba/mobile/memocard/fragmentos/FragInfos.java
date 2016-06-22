package br.edu.ifba.mobile.memocard.fragmentos;

//import android.app.Fragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import br.edu.ifba.mobile.memocard.R;

/**
 * Created by André Sobreira on 21/06/2016.
 */
public class FragInfos extends Fragment {

    private static FragInfos instancia = null;

    public static FragInfos getInstancia(){
        if(instancia == null){
            instancia = new FragInfos();
        }
        return instancia;
    }

    private View tela = null;

    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup vgrupo, Bundle bundle){
        tela = inflador.inflate(R.layout.frag_infos, vgrupo, false);
        return tela;
    }

}
