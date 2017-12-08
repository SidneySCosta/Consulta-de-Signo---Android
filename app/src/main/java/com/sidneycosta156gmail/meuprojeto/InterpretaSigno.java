package com.sidneycosta156gmail.meuprojeto;

/**
 * Created by sidney on 03/12/17.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class InterpretaSigno {
    //utilizando construtor da classe SIGNO com parametros
    private ArrayList<Signo> signos = new ArrayList<Signo>(){{  // INICIALIZACAO DO ARRAY LIST DOS SIGNOS

        // adicionando os objetos
       add(new Signo(20, 1, 18, 2,
               "Aquário", "@drawable/aquario"));
       add(new Signo(19, 2, 20, 3,
               "Peixes", "@drawable/peixes"));
       add(new Signo(21, 3, 19, 4,
               "Aries", "@drawable/aries"));
       add(new Signo(20, 4, 20, 5,
               "Touro", "@drawable/touro"));
       add(new Signo(21, 5, 20, 6,
               "Gemeos", "@drawable/gemeos"));
       add(new Signo(21, 6, 22, 7,
               "Cancer", "@drawable/cancer"));
       add(new Signo(23, 7, 22, 8,
               "Leao", "@drawable/leao"));
       add(new Signo(23, 8, 22, 9,
               "Virgem", "@drawable/virgem"));
       add(new Signo(23, 9, 22, 10,
               "Libra", "@drawable/libra"));
       add(new Signo(23, 10, 21, 11,
               "Escorpiao", "@drawable/escorpiao"));
       add(new Signo(22, 11, 21, 12,
               "Sagitário", "@drawable/sagitario"));
       add(new Signo(22, 12, 19, 1,
               "Capricornio", "@drawable/capricornio"));
 }};

    public InterpretaSigno(){
        //ordena a lista dos signos com o metodo SORT
        Collections.sort(signos, new Comparator<Signo>() {
            @Override
            public int compare(Signo signo, Signo signo2)
            {

                return  signo.getNome().compareTo(signo2.getNome());
            }
        });
    }

   public Signo interpreta(int dia, int mes) {
       //objeto do tipo Signo
       Signo signo = null;

       //foreach p/ percorrer a lista de signos para identificar ao qual corresponde com o parametro data recebido.
             for (Signo s : signos) {

                 //verificando se a data recebida por parametro corresponde ao signo atual
                 //mes = mes de inicio - dia > dia de inicio do signo
                 if (s.getMesinicio() == mes && dia >= s.getDiainicio()) {
                     signo = s;
                   break;
                     //mes = mes fim - dia < mes de fim do signo
                   } else if (s.getMesfim() == mes && dia <= s.getDiafim()) {
                      signo = s;

                    break;
                   }
             }
         return signo;
      }

    public ArrayList<Signo> getSignos() {
        return signos;
    }
}