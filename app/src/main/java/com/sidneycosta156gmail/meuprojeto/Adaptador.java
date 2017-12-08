package com.sidneycosta156gmail.meuprojeto;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sidney on 03/12/17.
 */

public class Adaptador extends ArrayAdapter<Signo> implements AdapterView.OnItemClickListener {
    Activity contextoAtual = null;
    ArrayList<Signo> arrayModelo = null;


    public Adaptador(@NonNull Activity context, ArrayList<Signo> array) {
        super(context, 0, array);
        contextoAtual = context;
        arrayModelo = array;
    }

    public View getView(int indice, View celulaReciclada, ViewGroup pai) {
        //se a celula reciclada for igual a null
        if (celulaReciclada == null) {
            celulaReciclada = LayoutInflater.from(contextoAtual).inflate(R.layout.celula_signo, null);
        }
        TextView data = (TextView) celulaReciclada.findViewById(R.id.textViewData);
        //concatena a celula do array modelo com DIA INICIO - MES INICIO - a - DIA FIM - MES FIM
        data.setText(arrayModelo.get(indice).getDiainicio() + "/" + arrayModelo.get(indice).getMesinicio() + " à " +
                arrayModelo.get(indice).getDiafim() + "/" + arrayModelo.get(indice).getMesfim());

        TextView nome = (TextView) celulaReciclada.findViewById(R.id.textViewNome);
        nome.setText(arrayModelo.get(indice).getNome());

        return celulaReciclada;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
}

