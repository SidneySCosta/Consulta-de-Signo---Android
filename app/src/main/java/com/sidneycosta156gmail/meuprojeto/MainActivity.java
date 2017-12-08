package com.sidneycosta156gmail.meuprojeto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import bancoBd.BancoController;

public class MainActivity extends AppCompatActivity {

    //varivaeis do tipo spinner
    private Spinner spinnerDia = null;
    private Spinner spinnerMes = null;

    //metodo pra validar datas em meses que não tem 31 dias
    private void validaData() {
        int dia = spinnerDia.getSelectedItemPosition(); // pegando o indice dos spinner
        int mes = spinnerMes.getSelectedItemPosition(); // pegando o indice dos spiner

        /****como os índices dos spinners começam a contar em 0, os dias e meses começam em 1*****/
        dia++; //incrementando o dia
        mes++; //incrementado o mês

        /****Se selecionado um dia inválido para o mês, é definido o dia como sendo o último dia válido do mês.****/
        //verificando o dia válido em Fevereiro
        if (dia > 29 && mes == 2) {
            spinnerDia.setSelection(28); //etando no spinner o dia válido para 28
            //verificando o dia valido para os meses de: ABRIL, JUNHO, SETEMBRO, NOVEMBRO
        } else if ((mes == 4 || mes == 6 || mes == 9 || mes == 11) && (dia > 30)) {
            spinnerDia.setSelection(29);//setando no spinner o dia válido para 29
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //referenciando as variaveis spinner
        spinnerDia = (Spinner) findViewById(R.id.spinnerDia); // objetos do tipo spinner do XML
        spinnerMes = (Spinner) findViewById(R.id.spinnerMes); // objetos do tipo spinner do XML


        //criação do adapter, objeto responsável por ligar o spinner com o array de itens definido no strings.xml.
        ArrayAdapter<CharSequence> adapter_dia = ArrayAdapter.createFromResource(this, R.array.dias,
                android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> adapter_mes = ArrayAdapter.createFromResource(this, R.array.meses,
                android.R.layout.simple_spinner_dropdown_item);

        // especificando o tipo de dropdown inserido dentro do objeto do array definindo como os itens serão exibidos no spinner.
        adapter_dia.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_mes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerDia.setAdapter(adapter_dia); // setando o adapter para o spinner dia
        spinnerMes.setAdapter(adapter_mes); // setando o adapter para o spinner mes


        /***tratando o evento quando um item é selecioando nos spinners***/
        spinnerDia.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    //metodo valida data é chamado
                    public void onItemSelected(AdapterView<?> parent,
                                               View view,
                                               int position,
                                               long id) {
                        validaData();
                    }

                    @Override
                    //
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });

        //instancia a variavel Button fazendo referencia ao XML
        Button enviar = (Button) findViewById(R.id.buttonEnviar);
        //metodo responsável para setar uma ação quando o botão for pressionado;
        enviar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                //buscando o indice selecioando nos spinners
                int posicaoDia = spinnerDia.getSelectedItemPosition();
                int posicaoMes = spinnerMes.getSelectedItemPosition();

                //incrementando o valor obtido para que os índices sejam equivalentes
                posicaoDia++;
                posicaoMes++;

                //intancia da classe interpreta signo
                InterpretaSigno Interpretador = new InterpretaSigno();
                //chamando o método interpreta, passando como argumento o dia e mês informado
                Signo signoResultado = Interpretador.interpreta(posicaoDia, posicaoMes);

                //conecta no bd
                BancoController crud = new BancoController(getBaseContext());
                String resultado;
                resultado = crud.insereDado(signoResultado.getNome(), posicaoDia + "/" + posicaoMes);
                //bundle - trafega dados atraves das activities
                Bundle args = new Bundle();
                //adicionando signo obtido
                args.putSerializable("resultado", signoResultado);
                //passagem de parametros entre as activities adicionando o bundle declarado acima
                Intent intent = new Intent(MainActivity.this, Resultado.class);
                intent.putExtra("signo", args);
                //inicia a activitie de resultado passando o intent que tem o bundle com o signo
                startActivity(intent); // envia da main Activity para o RESULTADO

            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_principal, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //BOTOES DO MENU
        if (id == R.id.action_signos) {

            Intent intent = new Intent(MainActivity.this, ActivityListaSignos.class);
            this.startActivity(intent);
            return true;
        } else if (id == R.id.action_historico) {

            Intent intent = new Intent(MainActivity.this, ActivityHistorico.class);
            this.startActivity(intent);
            return true;
        } else if (id == R.id.action_sobre) {

            Intent intent = new Intent(MainActivity.this, ActivitySobre.class);
            this.startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);

        }


    }


}

