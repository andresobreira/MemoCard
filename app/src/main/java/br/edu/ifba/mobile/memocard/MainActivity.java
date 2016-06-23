package br.edu.ifba.mobile.memocard;

import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;

import br.edu.ifba.mobile.memocard.BD.FachadaBD;
import br.edu.ifba.mobile.memocard.R;
import br.edu.ifba.mobile.memocard.fragmentos.FragCadastroCard;
import br.edu.ifba.mobile.memocard.fragmentos.FragCadastroUser;
import br.edu.ifba.mobile.memocard.fragmentos.FragInfos;
import br.edu.ifba.mobile.memocard.fragmentos.FragListaCards;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager paginador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        paginador = (ViewPager) findViewById(R.id.container);
        assert paginador != null;
        paginador.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        assert tabLayout != null;
        tabLayout.setupWithViewPager(paginador);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        paginador.addOnPageChangeListener(this);
        FachadaBD.criarInstancia(this.getApplicationContext());
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == 1) {
            FragListaCards.getInstancia().atualizar();
        } else if (position == 2) {
            FragCadastroCard.getInstancia().exibirCardSelecionado();
        } else if (position == 3) {
            FragCadastroUser.getInstancia().exibirUsuario();
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment frag = null;
            switch (position) {
                case 0:
                    frag = FragInfos.getInstancia();
                    break;
                case 1:
                    frag = FragListaCards.getInstancia();
                    break;
                case 2:
                    frag = FragCadastroCard.getInstancia();
                    break;
                case 3:
                    frag = FragCadastroUser.getInstancia();
                    break;
            }
            return frag;
        }

        @Override
        public int getCount() { // Show 4 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Infos";
                case 1:
                    return "Cards";
                case 2:
                    return "Inserir Card";
                case 3:
                    return "Seus Dados";
            }
            return null;
        }
    }
}
