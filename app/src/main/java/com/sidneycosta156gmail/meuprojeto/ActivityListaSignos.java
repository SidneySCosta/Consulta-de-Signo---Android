package com.sidneycosta156gmail.meuprojeto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

public class ActivityListaSignos extends AppCompatActivity {

    ListView listView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_signos);
        listView = (ListView) findViewById(R.id.listview);

        //ativando suporte para o menuBar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //instancia do interpreta signo
        InterpretaSigno interpretaSigno = new InterpretaSigno();
        //instancia do adaptador (parametrizando o interpreta signo)
        Adaptador adaptador = new Adaptador(this, interpretaSigno.getSignos());
        //chamando o listview dos signos e  setando o adapeter
        this.listView.setAdapter(adaptador);
        //click no listview dos signos
        this.listView.setOnItemClickListener(adaptador);
    }

    // metodo utilizado para retornar a tela anterior
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
    // botao home como padrao
        if (id == android.R.id.home) {
            finish();
        }
        return true;
    }

}
