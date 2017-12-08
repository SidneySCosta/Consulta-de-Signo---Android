package com.sidneycosta156gmail.meuprojeto;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Resultado extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        //pegando o bundle que tem o signo recebido pelo intent
        Bundle args = getIntent().getBundleExtra("signo");

        if (args != null) {
            //obtendo o objeto do tipo signo que foi passado no bundle
            Signo signoRecebido = (Signo) args.getSerializable("resultado");
            //referenciando a imagem que está dentro da pasta Drawble
            int imageResource = getResources().getIdentifier(signoRecebido.getImagem(), null, getPackageName());

            //atribuindo a  imagem do signo no imageView mostrando a imagem na tela
            Drawable res = getDrawable(imageResource);
            ImageView imagem_signo = (ImageView) findViewById(R.id.imgSigno);
            imagem_signo.setImageDrawable(res);

            //exibindo nos objetos TextView os dados do signo recebido por parametros
            TextView resultado = (TextView) findViewById(R.id.textSigno);
            TextView datas = (TextView) findViewById(R.id.textData);

            //concatenando o resultado a ser mostrado
            resultado.setText(signoRecebido.getNome());
            datas.setText("de " + signoRecebido.getDiainicio() +
                    "/" + signoRecebido.getMesinicio() +
                    " até " + signoRecebido.getDiafim() +
                    "/" + signoRecebido.getMesfim());
        }
            //botao voltar
        Button voltar = (Button) findViewById(R.id.buttonVoltar);
        voltar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });

    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }

        return true;
    }
}
