package com.sidneycosta156gmail.meuprojeto;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import bancoBd.BancoController;
import bancoBd.CriaBanco;

public class ActivityHistorico extends AppCompatActivity {
    private ListView lista;
    BancoController crud = null;
    SimpleCursorAdapter adaptador = null;
    Activity context = null;
    String[] nomeCampos;
    int[] idViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);
        //ativando suporte para o menuBar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // conecta com o bd
         crud = new BancoController(getBaseContext());
        // busca todos os registros
        Cursor cursor = crud.carregaDados();
        context = this;
        // nome dos campos na tabela
        nomeCampos = new String[]{CriaBanco.SIGNO, CriaBanco.MESDIA};
        //id dos widgets na celula de histórico
       idViews = new int[]{R.id.idsigno, R.id.idmesdia};
        // cria um adaptador para inflar e mapear a lista cim as informações do banco
         adaptador = new SimpleCursorAdapter(getBaseContext(),
                R.layout.celula_historico, cursor, nomeCampos, idViews, 0);
        lista = (ListView) findViewById(R.id.listView);
        //seta o adaptador da lista
        lista.setAdapter(adaptador);

       //adiciona uma acao no click na list p/ deletar o registro
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                crud.deletaRegistro((int)id);
                Toast.makeText(context, "registro apagado", Toast.LENGTH_LONG).show();
                adaptador = new SimpleCursorAdapter(getBaseContext(),
                        R.layout.celula_historico, crud.carregaDados(), nomeCampos, idViews, 0);
                lista = (ListView) findViewById(R.id.listView);
                lista.setAdapter(adaptador);//seta o adaptador da lista
                return false;
            }
        });

    }
//botao home do menu
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }

        return true;
    }
}

