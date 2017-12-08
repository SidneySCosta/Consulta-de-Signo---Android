package com.sidneycosta156gmail.meuprojeto;

import java.io.Serializable;

/**
 * Created by sidney on 03/12/17.
 */

public class Signo  implements Serializable{

    //atributos
    private int diainicio;
    private int diafim;
    private int mesinicio;
    private int mesfim;
    private String nome;
    private String imagem;

    //construtor sem parametros - requisito da interface seriazable
    public Signo(){

    }

    //sobrecarga de construtor - instancia com os parâmetros
    public Signo (int diainicio, int mesinicio, int diafim, int mesfim, String nome, String imagem){

        this.diainicio = diainicio;
        this.diafim = diafim;
        this.mesinicio = mesinicio;
        this.mesfim = mesfim;
        this.nome = nome;
        this.imagem = imagem;
    }

    public int getDiainicio() { return diainicio;}
    public int getDiafim() { return diafim;}
    public int getMesinicio() { return mesinicio;}
    public int getMesfim() { return mesfim;}
    public String getNome() { return nome;}
    public String getImagem() { return imagem;}



}
