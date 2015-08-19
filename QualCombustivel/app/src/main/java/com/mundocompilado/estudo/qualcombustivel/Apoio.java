package com.mundocompilado.estudo.qualcombustivel;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by jean.almeida on 14/08/2015.
 * Classe responsavel por dar suporte ao projeto
 *
 * salvar/consultar/remover dados
 *
 * verificar se ha conexao com a internet
 */
public class Apoio {
    public static String CHAVE = "SMS-";


    public static void salvar(String identificacao, String valor, Context context){

        //salvamos os dados na SharedPreferences do aplicativo
        //ele e um arquivo para salvar e resgatar informacoes pequenas
        //nao deve ser usado como um banco de dados comum de uma aplicacao

        SharedPreferences preferences = context.getSharedPreferences(CHAVE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(CHAVE + identificacao, valor);
        editor.apply();
    }

    public static Map<String, ?> consultar(Context context){

        //Resgatamos todos os dados salvos de acordo com a chave
        SharedPreferences preferences = context.getSharedPreferences(CHAVE, Context.MODE_PRIVATE);

        ArrayList<String> lista = new ArrayList<>();

         return preferences.getAll();
    }

    public static  void remover(String chave, Context context){
        //removemos todos os dados com a chave passada por parametro
        //no caso o numero de quem enviou o sms

        SharedPreferences preferences = context.getSharedPreferences(chave, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(CHAVE + chave);
        editor.apply();
    }

    public static boolean isOnline(Context context){

        //Verificamos a partir de um ConnectivityManager se ha conexao com a internet
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
